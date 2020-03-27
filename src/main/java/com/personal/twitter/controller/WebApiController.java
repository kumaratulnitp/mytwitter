package com.personal.twitter.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/web")
public class WebApiController {
	
	@PutMapping("/register")
	public String regiserUser(String userName, String name, String email) {
		return "work in progress";
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
