package com.personal.twitter.pojo;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@XmlRootElement
public class User {
	private int id;
	private String userName;
	private String name;
	private String email;
	private List<String> followers;
}
