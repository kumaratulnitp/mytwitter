package com.personal.twitter.pojo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class Comment {
	private int id;
	private String text;
	private int userId;
	private Date createdOn;
	private int tweetId;
}
