package com.social.portal.dao;

import java.util.List;
import java.io.IOException;
import com.social.portal.bean.Post;
import com.social.portal.bean.User;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.bson.Document;

@Repository
public interface SocialNetworkingDao{
	public void addUser(User user);
	public void removeUser(User user);
	public void addPost(Post post,CommonsMultipartFile[] imagePost,CommonsMultipartFile[] videoPost) throws IOException;
	public List<Document> fetchPublicPosts();
	public void deletePost(Post post);
}