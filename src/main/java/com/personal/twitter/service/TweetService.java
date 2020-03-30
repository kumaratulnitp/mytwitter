package com.personal.twitter.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.twitter.dao.CommentDao;
import com.personal.twitter.dao.TweetDao;
import com.personal.twitter.exception.BusinessException;
import com.personal.twitter.pojo.Comment;
import com.personal.twitter.pojo.Tweet;
import com.personal.twitter.webvo.TweetVO;

@Service
public class TweetService {

	@Autowired
	private TweetDao tweetDao;

	@Autowired
	private CommentDao commentDao;

	@Autowired
	private QueueService qService;

	public Tweet create(String text, String userId) {
		Tweet tweet = tweetDao.create(text, userId);
		qService.enqueue(tweet);
		return tweet;
	}

	public boolean like(int tweetId, String userName) throws BusinessException {
		return tweetDao.like(tweetId, userName);
	}

	public boolean comment(int tweetId, String userName, String text) throws BusinessException {
		return tweetDao.comment(tweetId, userName, text);
	}

	public Tweet find(int tweetId) throws BusinessException {
		return tweetDao.find(tweetId);
	}

	public TweetVO findComplete(int tweetId) throws BusinessException {
		Tweet tweet = tweetDao.find(tweetId);
		TweetVO tweetVO = TweetVO.builder()
				.id(tweet.getId())
				.text(tweet.getText())
				.createdOn(tweet.getCreatedOn())
				.userName(tweet.getUserName())
				.likedBy(tweet.getLikedBy())
				.build();

		
		if (tweet.getCommentIds() != null) {
			List<Comment> comments = new ArrayList<Comment>();
			for(int commentId:tweet.getCommentIds()) {
				Comment comment = commentDao.find(commentId);
				if(comment != null) {
					comments.add(comment);
				}
			}
			tweetVO.setComments(comments);
		}
		return tweetVO;
	}
}
