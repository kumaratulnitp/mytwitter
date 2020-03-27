package com.personal.twitter.pojo;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsFeed {
	
	/**
	 * For the sake of simplicity in first version, we will go 
	 * with list of twwets. Later on, me might think about ranking and probably 
	 * move to PriorityQueue or something.
	 */
	private List<Tweet> feedData;
}
