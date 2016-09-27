package com.social.portal.bean;

public class Like{
	private String likedBy;
	private String likeType;

	public String getLikedBy(){
		return likedBy;
	}
	public void setLikedBy(String likedBy){
		this.likedBy=likedBy;
	}

	public String getLikeType(){
		return likeType;
	}

	public void setLikeType(String likeType){
		this.likeType=likeType;
	}
}