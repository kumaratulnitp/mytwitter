package com.personal.twitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.pojo.User;

@Repository
public class NewsFeedDaoInMemory implements NewsFeed {
	
	private Map<String, List<Tweet>> newsfeeds = new ConcurrentHashMap<>();
	
	@Override
	public void updateNewsFeed(User user, Tweet tweet) {

		List<Tweet> userNewsFeed = newsfeeds.get(user.getUserName());
		if (userNewsFeed == null) {
			userNewsFeed = new ArrayList<Tweet>();
		}
		userNewsFeed.add(tweet);
		newsfeeds.put(user.getUserName(), userNewsFeed);
	}
	@Override
	public List<Tweet> get(String userName) {
		List<Tweet> userNewsFeed = newsfeeds.get(userName);
		return userNewsFeed;
		
	}

}
