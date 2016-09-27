package com.social.portal.bean;

public class Comment{
	private String commentedBy;
	private String comment;

	public String getCommentedBy(){
		return commentedBy;
	}

	public void setCommentedBy(String commentedBy){
		this.commentedBy=commentedBy;
	}

	public String getComment(){
		return comment;
	}

	public void setComment(String comment){
		this.comment=comment;
	}
}