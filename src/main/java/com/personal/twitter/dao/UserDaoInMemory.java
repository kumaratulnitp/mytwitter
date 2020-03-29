package com.personal.twitter.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;

@Repository
public class UserDaoInMemory implements UserDao{

	private Map<String, User> users = new ConcurrentHashMap<>();
	
	private AtomicInteger availableId = new AtomicInteger(0);
	
	@Override
	public User find(String userName) throws BusinessException {
		User user = users.get(userName);
		if (user == null) {
			throw new BusinessException("UserName does not exists");
		}
		return user;
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

	@Override
	public boolean follow(String userNameToFollow, String followerUserName) throws BusinessException {
		User userToFollow = users.get(userNameToFollow);
		User followerUser = users.get(followerUserName);
		if (userToFollow == null || followerUser == null) {
			throw new BusinessException("UserName does not exists");
		}
		List<String> followerUserNames = userToFollow.getFollowers();
		if (followerUserNames == null) {
			followerUserNames = new ArrayList<>();
		} 
		if (followerUserNames.contains(followerUserName)) {
			return false;
		}
		followerUserNames.add(followerUserName);
		userToFollow.setFollowers(followerUserNames);
		return true;
	}

	@Override
	public List<User> getAllFollowers(String userName)  throws BusinessException{
		User userToFollow = users.get(userName);
		if (userToFollow == null) {
			throw new BusinessException("UserName does not exists");
		}
		List<String> followersList = userToFollow.getFollowers();
		List<User> usersList = new ArrayList<User>();
		if (followersList != null) {
			usersList = followersList.stream()
				.map(followerUserName -> users.get(followerUserName))
				.collect(Collectors.toList());
		}
		return usersList;
	}
}
