package com.personal.twitter.dao;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;

public interface TweetDao {
	Tweet find(int tweetId) throws BusinessException;
	boolean like(int tweetId, String userName) throws BusinessException;
	boolean comment(int tweetId, String userName, String text) throws BusinessException;
	Tweet create(String text, String userName);
}
