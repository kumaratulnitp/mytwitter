package com.personal.twitter.pojo;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Tweet {
	private int id;
	private String text;
	private Date createdOn;
	private int userId;
	private List<Integer> likedBy;
	private List<Integer> commentIds;
	private String topComment;
}
