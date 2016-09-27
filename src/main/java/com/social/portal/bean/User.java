package com.social.portal.bean;

import java.util.List;
import com.social.portal.bean.Friend;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
public class User{

	@Id
	private String id;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private int role;
	//private List<Friend> friends;


	public int getRole(){
		return role;
	}

	public void setRole(int role){
		this.role=role;
	}

	public String getId(){
		return id;
	}

	public void setId(String id){
		this.id=id;
	}

	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username=username;
	}
	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password=password;
	}
	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName=firstName;
	}
	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName=lastName;
	}
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email=email;
	}

	/*public List<Friend> getFriends(){
		return friends;
	}

	public void setFriends(){
		this.friends=friends;
	}*/
}