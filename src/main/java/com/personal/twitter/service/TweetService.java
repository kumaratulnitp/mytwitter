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
	
	public Tweet create(String text, int userId) {
		return tweetDao.create(text, userId);
	}
	
	public boolean like(int tweetId, int userId) throws BusinessException {
		return tweetDao.like(tweetId, userId);
	}
	
	public boolean comment(int tweetId, int userId, String text) throws BusinessException {
		return tweetDao.comment(tweetId, userId, text);
	}
	
	public Tweet find(int tweetId) throws BusinessException {
		return tweetDao.find(tweetId);
	}
}
