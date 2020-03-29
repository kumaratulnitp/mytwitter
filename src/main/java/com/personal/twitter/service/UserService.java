package com.personal.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.twitter.dao.NewsFeed;
import com.personal.twitter.dao.UserDao;
import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.pojo.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NewsFeed newsFeed;
	
	public User find(String userName) throws BusinessException {
		return userDao.find(userName);
	}
	
	public User create(String userName, String name, String email) throws BusinessException {
		User user = userDao.create(userName, name, email);
		return user;
	}
	
	public boolean follow(String userNameToFollow, String followerUserName) throws BusinessException {
		return userDao.follow(userNameToFollow, followerUserName);
	}
	
	public List<User> findFollowers(String userName) throws BusinessException {
		return userDao.getAllFollowers(userName);
	}
	
	public List<Tweet> getNewsFeed(String userName) {
		List<Tweet> userNewsFeed = newsFeed.get(userName);
		return userNewsFeed;
	}
}
