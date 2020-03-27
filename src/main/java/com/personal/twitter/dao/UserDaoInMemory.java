package com.personal.twitter.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;

@Component
public class UserDaoInMemory implements UserDao{

	private Map<String, User> users = new ConcurrentHashMap<>();
	
	@Override
	public User findUser(String userName) throws BusinessException {
		if (isUserPresent(userName)) {
			throw new BusinessException("UserName does not exists");
		}
		return users.get(userName);
	}

	@Override
	public User createUser(String userName, String name, String email) throws BusinessException {
		if (isUserPresent(userName)) {
			throw new BusinessException("UserName already present");
		}
		User user = User.builder()
				.userName(userName)
				.name(name)
				.email(email)
				.build();
		return saveUser(user);
	}
	
	private boolean isUserPresent(String userName) {
		return users.containsKey(userName);
	}

	private User saveUser(User user) {
		return users.put(user.getUserName(), user);
	}
}
