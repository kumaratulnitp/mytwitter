package com.personal.twitter.service;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

import org.springframework.stereotype.Service;

import com.personal.twitter.pojo.Tweet;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class QueueService {
	Queue<Tweet> tweetQueue = new ArrayBlockingQueue<Tweet>(1000);
	
	public boolean enqueue(Tweet tweet) {
		boolean resp = false;
		try {
			resp =tweetQueue.add(tweet); 
		} catch(IllegalStateException e) {
			log.error("error in enqueue {}", e);
		}
		return resp;
	}
	
	public Tweet dequeue() {
		Tweet tweet = tweetQueue.poll();
		if (tweet == null) {
			log.info("queue is empty");
		}
		return tweet;
	}
	
	public boolean isEmpty() {
		return tweetQueue.isEmpty();
	}

}
