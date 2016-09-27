package com.social.portal.bean;

import java.io.Serializable;
import java.util.List;
import com.social.portal.bean.User;
import org.springframework.stereotype.Component;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Post{

	@Id
	private String id;
	private String postType;
	private String nameOfPost;
	private String postDescription;
	private String postedBy;
	private List<User> likes;
	private List<User> comments;
	private List<String> imageNames;
	private List<String> videoNames;

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

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
	public List<User> getLikes(){
		return likes;
	}
	public void setLikes(List<User> likes){
		this.likes=likes;
	}
	public List<User> getComments(){
		return comments;
	}
	public void setComments(List<User> comments){
		this.comments=comments;
	}

	public List<String> getImageNames(){
		return imageNames;
	}

	public void setImageNames(List<String> imageNames){
		this.imageNames=imageNames;
	}

	public List<String> getVideoNames(){
		return videoNames;
	}

	public void setVideoNames(List<String> videoNames){
		this.videoNames=videoNames;
	}
}