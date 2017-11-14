package com.medidonate.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.medidonate.model.Feed;
import com.medidonate.model.UserFeeds;
import com.medidonate.service.FeedService;
import com.medidonate.utils.AbstractResponse;

@RestController
public class FeedController {

	private final Logger LOGGER = LoggerFactory.getLogger(FeedController.class);
	
	@Autowired
	FeedService feedService;
	
	@RequestMapping(path ="/uploadImage", method = RequestMethod.POST)
	public String uploadFeed(@RequestParam("upload") MultipartFile image) {
	
		LOGGER.info("uploadFeed() : uploading file...");
		LOGGER.info("uploadFeed() : file = "+image);
		
		String imgUrl = feedService.uploadFeed(image);
		LOGGER.info("uploadFeed() : imgUrl = "+imgUrl);
		return imgUrl;
	}
	
	@RequestMapping("/files/{filename:.+}")
	@ResponseBody
	public ResponseEntity<Resource> getFile(@PathVariable String filename) {
		Resource file = feedService.loadFile(filename);
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
				.body(file);
	}
	
	@RequestMapping(path="/postFeed",method = RequestMethod.POST, produces= {"application/json"})
	public AbstractResponse postFeed(@RequestParam("upload") MultipartFile image,@RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("userId") String userId, @RequestParam("address") String address,@RequestParam("category") String category) {
		
		Feed feed = new Feed();
		feed.setTitle(title);
		feed.setUserId(Integer.valueOf(userId));
		feed.setActive("1");
		feed.setAddress(address);
		feed.setCategory(category);
		feed.setDescription(description);
		
		return feedService.postFeed(feed,image);
	}
	
	@RequestMapping(path="/getFeeds", method=RequestMethod.GET, produces= {"application/json"})
	public List<UserFeeds> getFeeds(){
		
		
		return feedService.getFeeds();
	}
}
