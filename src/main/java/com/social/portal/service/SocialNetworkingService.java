package com.social.portal.service;

import java.util.List;
import java.io.IOException;
import com.social.portal.bean.Post;
import com.social.portal.bean.User;
import org.springframework.stereotype.Service;
import org.bson.Document;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

@Service
public interface SocialNetworkingService{
	public void addUser(User user);
	public void removeUser(User user);
	public void addPost(Post post,CommonsMultipartFile[] imagePost,CommonsMultipartFile[] videoPost) throws IOException;
	public List<Document> fetchPublicPosts();
	public void deletePost(Post post);
}