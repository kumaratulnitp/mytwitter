package com.personal.twitter.dao;

import com.personal.twitter.pojo.Comment;

public interface CommentDao {
	Comment create(String text, int userId, int tweetId);
}
