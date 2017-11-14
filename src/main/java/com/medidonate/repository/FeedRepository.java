package com.medidonate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medidonate.model.Feed;

public interface FeedRepository extends JpaRepository<Feed, Integer> {

	public List<Feed> findAll();
}
