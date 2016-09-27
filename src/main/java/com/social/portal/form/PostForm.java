package com.social.portal.form;

import java.io.Serializable;
import org.springframework.stereotype.Component;

@Component
public class PostForm implements Serializable{
	private static final long serialVersionUID = 1L;

	private String postType;
	private String nameOfPost;
	private String postDescription;

	public String getPostType(){
		return postType;
	}

	public void setPostType(String postType){
		this.postType=postType;
	}

	public String getNameOfPost(){
		return nameOfPost;
	}

	public void setNameOfPost(String nameOfPost){
		this.nameOfPost=nameOfPost;
	}

	public String getPostDescription(){
		return postDescription;
	}

	public void setPostDescription(String postDescription){
		this.postDescription=postDescription;
	}
} 
