package com.personal.twitter.dao;

import java.util.List;

import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.pojo.User;

public interface NewsFeedDao {
	void updateNewsFeed(User user, Tweet tweet);

	List<Tweet> get(String userName);
}
