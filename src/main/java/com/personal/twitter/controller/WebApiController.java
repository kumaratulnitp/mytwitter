package com.personal.twitter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.User;
import com.personal.twitter.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController(value = "/web")
@Slf4j
public class WebApiController {
	
	@Autowired
	private UserService userService;
	
	@PutMapping("/user")
	public ResponseEntity<User> regiserUser(String userName, String name, String email) {
		ResponseEntity<User> response = null;
		User user = null;
		try {
			user = userService.create(userName, name, email);
		} catch (BusinessException e) {
			log.error("User not created {}" ,e);
		}
		response = new ResponseEntity<User>(user, user == null ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
		return response;
	}
	
	@PutMapping("/tweet")
	public void tweet(String userName, String bodyText) {

	}
	
	@PutMapping("/follow")
	public void follow(String userName, String userNameToFollow) {

	}
	
	@PutMapping("/like")
	public void like(String userName, int tweetId) {

	}
	
	@PutMapping("/comment")
	public void comment(String userName, int tweetId, String commentBody) {

	}	
	
	@GetMapping("/newFeed")
	public void newsFeed(String userName) {

	}	
}
