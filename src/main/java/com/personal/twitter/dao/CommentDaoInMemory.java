package com.personal.twitter.dao;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Repository;

import com.personal.twitter.pojo.Comment;

@Repository
public class CommentDaoInMemory implements CommentDao {
	
	private Map<Integer, Comment> comments = new ConcurrentHashMap<>();
	
	private AtomicInteger availableId = new AtomicInteger(0);
	
	@Override
	public Comment create(String text, String userName, int tweetId) {
		Comment comment = Comment.builder()
				.id(availableId.getAndIncrement())
				.text(text)
				.userName(userName)
				.createdOn(new Date())
				.tweetId(tweetId)
				.build();
		
		comments.put(comment.getId(), comment);
		return comment;
	}

}
