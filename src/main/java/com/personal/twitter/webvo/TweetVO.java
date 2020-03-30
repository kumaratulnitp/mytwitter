package com.personal.twitter.webvo;

import java.util.Date;
import java.util.List;

import com.personal.twitter.pojo.Comment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TweetVO {
	private int id;
	private String text;
	private Date createdOn;
	private String userName;
	private List<String> likedBy;
	private List<Comment> comments;
}
