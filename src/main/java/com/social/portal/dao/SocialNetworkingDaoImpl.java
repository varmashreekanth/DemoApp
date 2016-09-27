package com.social.portal.dao;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import  java.io.IOException;
import org.apache.log4j.Logger;

import com.social.portal.dao.SocialNetworkingDao;
import com.social.portal.bean.Post;
import com.social.portal.bean.User;
import com.social.portal.util.MongoUtility;


import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mongodb.DB;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import org.bson.Document;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import static com.mongodb.client.model.Filters.*;

@Component
@Qualifier("socialNetworkingDao")
public class SocialNetworkingDaoImpl implements SocialNetworkingDao{

	private static final Logger LOG = Logger.getLogger(SocialNetworkingDaoImpl.class);
	private static final String POST_COLLECTION_NAME="post";
	private static final String USER_COLLECTION_NAME="user";
	private static final String IMAGE_PATH="F:/MyWorldImages/";
	private static final String VIDEO_PATH="F:/MyWorldVideos/";


	@Autowired
	private MongoTemplate mongoTemplate;

	

	/*private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory(){
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory=sessionFactory;
	}*/

	@Override
	public void addUser(User user){
		LOG.info("Entry :: addUser in dao layer");
		if(!mongoTemplate.collectionExists(User.class)){
			mongoTemplate.createCollection(User.class);
		}
		user.setId(UUID.randomUUID().toString());
		mongoTemplate.insert(user,USER_COLLECTION_NAME);
		LOG.info("Entry :: addUser in dao layer");
	}

	@Override
	public void removeUser(User user){
		LOG.info("Entry :: removeUser in dao layer");
		mongoTemplate.remove(user,USER_COLLECTION_NAME);
		LOG.info("Exit :: removeUser in dao layer");
	}

	@Override
	public void addPost(Post post,CommonsMultipartFile[] imagePost,CommonsMultipartFile[] videoPost) throws IOException{
		LOG.info("Entry :: addPost in dao layer");
		List<String>imageNames = new ArrayList<String>();
		List<String>videoNames = new ArrayList<String>();
		//sesionFactory.getCurrentSession.saveOrUpdate(post);
		if(!mongoTemplate.collectionExists(Post.class)){
			mongoTemplate.createCollection(Post.class);
		}
		post.setId(UUID.randomUUID().toString());

		LOG.info("imagePost-->"+imagePost);
		LOG.info("videoPost-->"+videoPost);
		/*Image uploaded*/
		if (imagePost != null && imagePost.length > 0) {
            for (CommonsMultipartFile imageFile : imagePost){
                 File uploadImageFile = new File(IMAGE_PATH + imageFile.getOriginalFilename());
                LOG.info("Saving file: " + imageFile.getOriginalFilename());
                 
                if (!imageFile.getOriginalFilename().equals("")) {
                    imageFile.transferTo(uploadImageFile);
                }
                MongoUtility.saveImageIntoMongo(uploadImageFile,IMAGE_PATH,imageFile.getOriginalFilename());
                imageNames.add(imageFile.getOriginalFilename());
            }
        }
        /*End*/
        /*Video Upload*/
        if (videoPost != null && videoPost.length > 0) {
            for (CommonsMultipartFile videoFile : videoPost){
                 File uploadVideoFile = new File(VIDEO_PATH + videoFile.getOriginalFilename());
                LOG.info("Saving file: " + videoFile.getOriginalFilename());
                 
                if (!videoFile.getOriginalFilename().equals("")) {
                    videoFile.transferTo(uploadVideoFile);
                }
                MongoUtility.saveVideoIntoMongo(uploadVideoFile,VIDEO_PATH,videoFile.getOriginalFilename());
                videoNames.add(videoFile.getOriginalFilename());
            }
        }
        /*End*/
        post.setImageNames(imageNames);
        post.setVideoNames(videoNames);

		mongoTemplate.insert(post,POST_COLLECTION_NAME);
		LOG.info("Exit :: addPost in dao layer");
	}

	@Override
	public List<Document> fetchPublicPosts(){
		LOG.info("Entry :: fetchPublicPosts() in the dao layer");
		final List<Document> publicPosts = new ArrayList<Document>();
		String nameOfTheDb = "postDB";//later on take it from the properties file instead of hardcoding
		MongoDatabase db = MongoUtility.getMongoDataBaseInstance(nameOfTheDb);
		MongoCollection collection = db.getCollection(POST_COLLECTION_NAME);
		//DBCursor cursor = collection.find(eq("postedBy","public"));

		//FindIterable it = collection.find(eq("postType","Public"));
		FindIterable<Document> it = collection.find(new Document("postType","Public"));
		//it.into(publicPosts);


		it.forEach(new Block<Document>() {
		    @Override
		    public void apply(final Document document) {
		        publicPosts.add(document);
		    }
		});

		/*while(cursor.hasNext()){
			publicPosts.add((Post)cursor.next());
		}*/
		LOG.info("publicPosts-->"+publicPosts.size());
		LOG.info("Exit :: fetchPublicPosts() in the dao layer");
		return publicPosts;
	}

	@Override
	public void deletePost(Post post){
		LOG.info("Entry :: deletePost in dao layer");
		mongoTemplate.remove(post,POST_COLLECTION_NAME);
		LOG.info("Exit :: deletePost in dao layer");
	}

	
}