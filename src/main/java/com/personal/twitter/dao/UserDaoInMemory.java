package com.personal.twitter.dao;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;

@Repository
public class UserDaoInMemory implements UserDao{

	private Map<String, User> users = new ConcurrentHashMap<>();
	
	private AtomicInteger availableId = new AtomicInteger(0);
	
	@Override
	public User find(String userName) throws BusinessException {
		if (isPresent(userName)) {
			throw new BusinessException("UserName does not exists");
		}
		return users.get(userName);
	}

	@Override
	public User create(String userName, String name, String email) throws BusinessException {
		if (isPresent(userName)) {
			throw new BusinessException("UserName already present");
		}
		
		User user = User.builder()
				.id(availableId.getAndIncrement())
				.userName(userName)
				.name(name)
				.email(email)
				.build();
		return save(user);
	}
	
	private boolean isPresent(String userName) {
		return users.containsKey(userName);
	}

	private User save(User user) {
		users.put(user.getUserName(), user);
		return user;
	}
}
