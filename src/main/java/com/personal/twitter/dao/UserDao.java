package com.personal.twitter.dao;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;

public interface UserDao {
	User findUser(String userName) throws BusinessException;
	User createUser(String userName, String name, String email) throws BusinessException;
}
