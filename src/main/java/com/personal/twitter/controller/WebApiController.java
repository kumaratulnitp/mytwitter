package com.personal.twitter.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.pojo.User;
import com.personal.twitter.service.TweetService;
import com.personal.twitter.service.UserService;
import com.personal.twitter.webvo.TweetVO;

import lombok.extern.slf4j.Slf4j;

@RestController(value = "/web")
@Slf4j
public class WebApiController {

	@Autowired
	private UserService userService;

	@Autowired
	private TweetService tweetService;

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
	public ResponseEntity<Tweet> tweet(String userName, String text) {
		ResponseEntity<Tweet> response = null;
		try {
			userService.find(userName);
		} catch (BusinessException e) {
			log.error("User not found");
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		if (response == null) {
			Tweet tweet = tweetService.create(text, userName);
			response = new ResponseEntity<Tweet>(tweet, HttpStatus.ACCEPTED);
		}
		return response;
	}

	@GetMapping("/tweet")
	public ResponseEntity<Tweet> getTweet(int tweetId) {
		ResponseEntity<Tweet> response = null;
		Tweet tweet = null;
		try {
			tweet = tweetService.find(tweetId);
			response = new ResponseEntity<Tweet>(tweet, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			log.error("Tweet not found {}", e.getMessage());
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return response;
	}
	
	@GetMapping("/tweetDetail")
	public ResponseEntity<TweetVO> detailTweet(int tweetId) {
		ResponseEntity<TweetVO> response = null;
		TweetVO tweet = null;
		try {
			tweet = tweetService.findComplete(tweetId);
			response = new ResponseEntity<>(tweet, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			log.error("Tweet not found {}", e.getMessage());
			response = new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	@GetMapping("/followers")
	public ResponseEntity<List<User>> follow(String userName) {
		List<User> followers = null;
		try {
			followers = userService.findFollowers(userName);
			return new ResponseEntity<List<User>>(followers, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			log.error("exception {}", e.getMessage());
			return new ResponseEntity<List<User>>(followers, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/follow")
	public ResponseEntity<Boolean> follow(String userNameToFollow, String followerUserName) {
		boolean result = false;
		try {
			result = userService.follow(userNameToFollow, followerUserName);
			return new ResponseEntity<Boolean>(result, HttpStatus.ACCEPTED);
		} catch (BusinessException e) {
			log.error("Exception {}" , e);
		}
		return new ResponseEntity<Boolean>(result, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/like")
	public ResponseEntity<String> like(String userName, int tweetId) {
		ResponseEntity<String> response = null;
		try {
			userService.find(userName);
			tweetService.like(tweetId, userName);
		} catch (BusinessException e) {
			log.error("Not able to complete request {}", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		if (response == null) {
			response = new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
		}
		return response;
	}

	@PutMapping("/comment")
	public ResponseEntity<String> comment(String userName, int tweetId, String text) {
		ResponseEntity<String> response = null;
		try {
			userService.find(userName);
			tweetService.comment(tweetId, userName, text);
		} catch (BusinessException e) {
			log.error("Not able to complete request {}", e.getMessage());
			response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		if (response == null) {
			response = new ResponseEntity<>("Success", HttpStatus.ACCEPTED);
		}
		return response;
	}	

	@GetMapping("/newsFeed")
	public ResponseEntity<List<Tweet>> newsFeed(String userName) {
		List<Tweet> newsFeed = null;
		try {
			userService.find(userName);
			newsFeed = userService.getNewsFeed(userName);
		} catch (BusinessException e) {
			log.error("Not able to complete request {}", e.getMessage());
			return new ResponseEntity<List<Tweet>>(newsFeed, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<Tweet>>(newsFeed, HttpStatus.OK);
	}	
	
	@GetMapping(value = "/testValid", produces = { MediaType.APPLICATION_JSON_VALUE , MediaType.APPLICATION_XML_VALUE})
	@ResponseBody
	public User textValidInJson(@NotNull @RequestParam("name") String name) {
		User user = User.builder()
				.id(1)
				.userName(name)
				.name(name)
				.email(name.concat("@gmail.com"))
				.build();
		return user;
	}
	
}
