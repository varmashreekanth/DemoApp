package com.social.portal.bean;

public class Friend{

	private String id;
	private boolean is_accepted;

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public boolean getIs_accepted(){
		return is_accepted;
	}

	public void setIs_accepted(boolean is_accepted){
		this.is_accepted=is_accepted;
	}
}