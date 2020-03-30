package com.personal.twitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.pojo.User;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class NewsFeedDaoInMemory implements NewsFeedDao {

	//private Map<String, List<Tweet>> newsfeeds = new ConcurrentHashMap<>();

	private Map<String, List<Integer>> newsfeedIds = new ConcurrentHashMap<>();

	@Autowired
	private TweetDao tweetDao;

	@Override
	public void updateNewsFeed(User user, Tweet tweet) {
		List<Integer> userNewsFeed = newsfeedIds.get(user.getUserName());
		if (userNewsFeed == null) {
			userNewsFeed = new ArrayList<>();
		}
		userNewsFeed.add(tweet.getId());
		newsfeedIds.put(user.getUserName(), userNewsFeed);
	}

	@Override
	public List<Tweet> get(String userName) {
		List<Integer> userNewsFeedIds = newsfeedIds.get(userName);
		List<Tweet> renderedNewsFeed = new ArrayList<Tweet>()  ; 
		if (userNewsFeedIds != null) {
			for(Integer tweetId: userNewsFeedIds) {
				try {
					Tweet tweet = tweetDao.find(tweetId);
					renderedNewsFeed.add(tweet);
				} catch (BusinessException e) {
					log.error("tweet id {} not found", tweetId);
				}
			}
		}
		return renderedNewsFeed;
	}

	/*
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
	 */

}
