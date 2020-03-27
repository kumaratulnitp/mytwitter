package com.personal.twitter.pojo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private int id;
	private String userName;
	private String name;
	private String email;
}
