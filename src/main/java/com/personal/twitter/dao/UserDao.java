package com.personal.twitter.dao;

import java.util.List;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;

public interface UserDao {
	User find(String userName) throws BusinessException;
	User create(String userName, String name, String email) throws BusinessException;
	boolean follow(String userNameToFollow, String followerUserName) throws BusinessException;
	List<User> getAllFollowers(String userName) throws BusinessException;
}
