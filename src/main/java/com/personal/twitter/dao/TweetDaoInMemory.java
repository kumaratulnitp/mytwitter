package com.personal.twitter.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Comment;
import com.personal.twitter.pojo.Tweet;

@Repository
public class TweetDaoInMemory implements TweetDao{

	private Map<Integer, Tweet> tweets = new ConcurrentHashMap<>();
	
	private AtomicInteger availableId = new AtomicInteger(0);
	
	@Autowired
	private CommentDao commentDao;
	
	@Override
	public Tweet create(String text, int userId) {
		Tweet tweet = Tweet.builder()
				.id(availableId.getAndIncrement())
				.text(text)
				.createdOn(new Date())
				.userId(userId)
				.build();
		save(tweet);
		return tweet;
	}

	@Override
	public boolean like(int tweetId, int userId) throws BusinessException {
		Tweet tweet = find(tweetId);
		List<Integer> likes = tweet.getLikedBy();
		if (likes == null) {
			likes = new ArrayList<Integer>();
		} else if (likes.contains(userId)) {
			return false;
		}
		likes.add(userId);
		tweet.setLikedBy(likes);
		return true;
	}

	@Override
	public boolean comment(int tweetId, int userId, String text) throws BusinessException {
		Tweet tweet = find(tweetId);
		List<Integer> commentIds = tweet.getCommentIds();
		if (commentIds == null) {
			commentIds = new ArrayList<>();
		}
		Comment comment = commentDao.create(text, userId, tweetId);
		commentIds.add(comment.getId());
		tweet.setCommentIds(commentIds);
		tweet.setTopComment(text);
		return true;
	}

	@Override
	public Tweet find(int tweetId) throws BusinessException {
		Tweet tweet = tweets.get(tweetId);
		if (tweet == null) {
			throw new BusinessException("Tweet not found");
		}
		return tweet;
	}

	private Tweet save(Tweet tweet) {
		tweets.put(tweet.getId(), tweet);
		return tweet;
	}

}
