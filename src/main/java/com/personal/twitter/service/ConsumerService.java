package com.personal.twitter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.personal.twitter.dao.NewsFeed;
import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.pojo.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ConsumerService {
	
	@Autowired
	private QueueService queueService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private NewsFeed newsFeed;
	
	@Scheduled(initialDelay = 3000, fixedDelay = 3000)
	public void updateFeeds() {
		log.info("newsfeed consumer starts");
		//while (true) {
			Tweet tweet = queueService.dequeue();
			if (tweet == null) {
				/*log.info("sleep for 2000 second");
				try {
					Thread.sleep(2000L);
				} catch (InterruptedException e) {
					log.error("Error in sleeping {}", e.getMessage());
				}*/
				
			} else {
				//get followers
				//loop through users feed
				//put this tweet.
				String userName = tweet.getUserName();
				List<User> users = null;
				try {
					 users= userService.findFollowers(userName);
				} catch (BusinessException e) {
					log.error("invalid tweet");
				} 
				if (users != null) {
					users.stream()
						.forEach(user -> {
							newsFeed.updateNewsFeed(user, tweet);
						});
				}
			}
		//}
	}
}
