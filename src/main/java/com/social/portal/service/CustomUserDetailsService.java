package com.social.portal.service;


	import java.util.ArrayList;
	import java.util.List;
	import org.apache.log4j.Logger;
	import com.social.portal.bean.User;
	 
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.data.mongodb.core.MongoOperations;
	import org.springframework.data.mongodb.core.MongoTemplate;
	import org.springframework.data.mongodb.core.query.Criteria;
	import org.springframework.data.mongodb.core.query.Query;
	import org.springframework.security.core.GrantedAuthority;
	import org.springframework.security.core.authority.SimpleGrantedAuthority;
	import org.springframework.security.core.userdetails.UserDetails;
	import org.springframework.security.core.userdetails.UserDetailsService;
	import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService{

	private static final Logger LOG = Logger.getLogger(CustomUserDetailsService.class);
	private MongoTemplate mongoTemplate;
 
	 public UserDetails loadUserByUsername(String username)
	 throws UsernameNotFoundException {
	 	LOG.info("Entry :: loadUserByUsername");
	 User user = getUserDetail(username);
	 LOG.info("username "+username);
	 org.springframework.security.core.userdetails.User userDetail = new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),true,true,true,true,getAuthorities(user.getRole()));
	LOG.info("Exit :: loadUserByUsername"); 
	 return userDetail;
	 }
	 
	@Autowired
	 public void setMongoTemplate(MongoTemplate mongoTemplate) {
	 this.mongoTemplate = mongoTemplate;
	 }
	 
	 public List<GrantedAuthority> getAuthorities(Integer role) {
	 	LOG.info("Entry :: getAuthorities");
	 List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
	 if (role.intValue() == 1) {
	 authList.add(new SimpleGrantedAuthority("ROLE_USER"));
	 authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
	 } else if (role.intValue() == 2) {
	 authList.add(new SimpleGrantedAuthority("ROLE_USER"));
	 }
	 LOG.info("Exit :: getAuthorities");
	 return authList;
	 }
	 
	 public User getUserDetail(String username){
	 	LOG.info("Entry :: getUserDetail");
	 MongoOperations mongoOperation = (MongoOperations)mongoTemplate;
	 User user = mongoOperation.findOne(
	 new Query(Criteria.where("username").is(username)),
	 User.class);
	 LOG.info("user "+user.toString());
	 LOG.info("Exit :: getUserDetail");
	 return user;
	 }

}

