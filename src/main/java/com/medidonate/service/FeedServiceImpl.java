package com.medidonate.service;

import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.medidonate.controller.FeedController;
import com.medidonate.model.Feed;
import com.medidonate.model.User;
import com.medidonate.model.UserFeeds;
import com.medidonate.repository.FeedRepository;
import com.medidonate.repository.UserRepository;
import com.medidonate.utils.AbstractResponse;

@Service
public class FeedServiceImpl implements FeedService {

	private final Logger LOGGER = LoggerFactory.getLogger(FeedServiceImpl.class);
	private final Path rootLocation = Paths.get("upload-dir");
	
	@Autowired
	FeedRepository feedRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public String uploadFeed(MultipartFile file) {
		
		LOGGER.info("uploadFeed() : uploading file...");
		LOGGER.info("uploadFeed() : file = "+file);
		String imgUrl = null;
		
		try {
            Files.copy(file.getInputStream(), this.rootLocation.resolve(file.getOriginalFilename()));
            Path pathToFile = rootLocation.resolve(file.getOriginalFilename());
            Resource resource = new UrlResource(pathToFile.toUri());
            imgUrl = pathToFile.getFileName().toString();
            LOGGER.info("uploadFeed() : image url = "+imgUrl);
        } catch (Exception e) {
        	e.printStackTrace();
        }
		
		return imgUrl;
	}

	@Override
	public Resource loadFile(String fileName) {
		
		try {
            Path file = rootLocation.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if(resource.exists() || resource.isReadable()) {
                return resource;
            }else{
            	throw new RuntimeException("FAIL!");
            }
        } catch (MalformedURLException e) {
        	e.printStackTrace();
        	throw new RuntimeException("FAIL!");
        }
	}

	@Override
	public AbstractResponse postFeed(Feed feed, MultipartFile image) {
		feed.setCreationDate(new Date());
		feed.setPic(this.uploadFeed(image));
		
		feedRepository.save(feed);
		AbstractResponse ar = new AbstractResponse();
		ar.setStatusCode("200");
		ar.setMessage("Success.");
		
		return ar;
	}

	@Override
	public List<UserFeeds> getFeeds() {
		List<Feed> feedList = feedRepository.findAll();
		List<UserFeeds> userFeedsList = new ArrayList<>();
		
		if(feedList != null) {
			for (Iterator iterator = feedList.iterator(); iterator.hasNext();) {
				Feed feed = (Feed) iterator.next();
				
				String imgName = feed.getPic();
				String fullImgPath = "http://192.168.1.13:2020/files/"+imgName;
				
				feed.setPic(fullImgPath);
				
				User user = userRepository.findById(feed.getUserId());
				
				UserFeeds userFeeds = new UserFeeds();
				userFeeds.setFeed(feed);
				
				String userImg = user.getPic();
				String userImgPath = "http://192.168.1.13:2020/files/"+userImg;
				
				user.setPic(userImgPath);
						
				userFeeds.setUser(user);
				
				userFeedsList.add(userFeeds);
				
			}
		}
		return userFeedsList;
	}

}
