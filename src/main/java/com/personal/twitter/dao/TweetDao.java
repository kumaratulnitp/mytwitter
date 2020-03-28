package com.personal.twitter.dao;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;

public interface TweetDao {
	Tweet create(String text, int userId);
	boolean like(int tweetId, int userId) throws BusinessException;
	boolean comment(int tweetId, int userId, String text) throws BusinessException;
	Tweet find(int tweetId) throws BusinessException;
}
