package com.social.portal.service;

import java.util.List;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.io.IOException;

import com.social.portal.bean.Post;
import com.social.portal.bean.User;
import com.social.portal.service.SocialNetworkingService;
import com.social.portal.dao.SocialNetworkingDao;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.bson.Document;


@Component
@Qualifier("socialNetworkingService")
public class SocialNetworkingServiceImpl implements SocialNetworkingService{

	private static final Logger LOG = Logger.getLogger(SocialNetworkingServiceImpl.class);

	@Autowired
	private SocialNetworkingDao socialNetworkingDao;

	@Override
	public void addUser(User user){
		LOG.info("Entry :: addUser in the service layer");
		socialNetworkingDao.addUser(user);
		LOG.info("Exit :: addUser in the service layer");
	}

	@Override
	public void removeUser(User user){
		LOG.info("Entry :: removeUser in the service layer");
		socialNetworkingDao.removeUser(user);
		LOG.info("Exit :: removeUser in the service layer");
	}

	@Override
	public void addPost(Post post,CommonsMultipartFile[] imagePost,CommonsMultipartFile[] videoPost) throws IOException{
		LOG.info("Entry :: addPost  in the service layer");
		/*Post post = new Post();
		if(postForm.getPostType() != null && !("").equals(postForm.getPostType())){
			post.setPostType(postForm.getPostType());
		}
		else{
			post.setPostType("");
		}

		if(postForm.getNameOfPost() != null && !("").equals(postForm.getNameOfPost())){
			post.setNameOfPost(postForm.getNameOfPost());
		}
		else{
			post.setNameOfPost("");
		}

		if(postForm.getPostDescription() != null && !("").equals(postForm.getPostDescription())){
			post.setPostDescription(postForm.getPostDescription());
		}
		else{
			post.setPostDescription("");
		}*/

		socialNetworkingDao.addPost(post,imagePost,videoPost);
		LOG.info("Exit :: addPost  in the service layer");
	}

	@Override
	public List<Document> fetchPublicPosts(){
		LOG.info("Entry :: fetchPublicPosts in the service layer");
		List<Document> publicPosts = new ArrayList<Document>();
		publicPosts=socialNetworkingDao.fetchPublicPosts();
		LOG.info("Exit :: fetchPublicPosts in the service layer");
		return publicPosts;
	}

	@Override
	public void deletePost(Post post){
		LOG.info("Entry :: deletePost in the service layer");
		socialNetworkingDao.deletePost(post);
		LOG.info("Exit :: deletePost in the service layer");
	}
}

