package com.personal.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.twitter.dao.TweetDao;
import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;

@Service
public class TweetService {
	
	@Autowired
	private TweetDao tweetDao;
	
	@Autowired
	private QueueService qService;
	
	public Tweet create(String text, String userId) {
		Tweet tweet = tweetDao.create(text, userId);
		qService.enqueue(tweet);
		return tweet;
	}
	
	public boolean like(int tweetId, String userName) throws BusinessException {
		return tweetDao.like(tweetId, userName);
	}
	
	public boolean comment(int tweetId, String userName, String text) throws BusinessException {
		return tweetDao.comment(tweetId, userName, text);
	}
	
	public Tweet find(int tweetId) throws BusinessException {
		return tweetDao.find(tweetId);
	}
}
