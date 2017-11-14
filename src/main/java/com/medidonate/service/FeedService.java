package com.medidonate.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.medidonate.model.Feed;
import com.medidonate.model.UserFeeds;
import com.medidonate.utils.AbstractResponse;

public interface FeedService {

	public String uploadFeed(MultipartFile file);
	public Resource loadFile(String fileName);
	public AbstractResponse postFeed(Feed feed, MultipartFile image);
	public List<UserFeeds> getFeeds();
}
