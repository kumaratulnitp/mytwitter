package com.personal.twitter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.twitter.dao.UserDao;
import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public User find(String userName) throws BusinessException {
		return userDao.find(userName);
	}
	
	public User create(String userName, String name, String email) throws BusinessException {
		User user = userDao.create(userName, name, email);
		return user;
	}
}
