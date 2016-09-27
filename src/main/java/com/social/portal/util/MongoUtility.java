package com.social.portal.util;

import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import com.mongodb.Mongo;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.DBCollection;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;

public class MongoUtility{

	private static final Logger LOG = Logger.getLogger(MongoUtility.class);
	
	public static DB getMongoDBInstance(String nameOfTheDb){

		LOG.info("Entry :: getMongoDBInstance");

		Mongo mongo = new Mongo("localhost",27017);
		DB db = mongo.getDB(nameOfTheDb);
		DBCollection dbCol = db.getCollection("imageColl");

		LOG.info("Exit :: getMongoDBInstance");

		return db;
	}

	public static GridFS getGridFSForImages(DB db,String gridFSNamespace){

		LOG.info("Entry :: getGridFSForImages");

		GridFS gfsPhoto = new GridFS(db,gridFSNamespace);

		LOG.info("Exit :: getGridFSForImages");

		return gfsPhoto;
	}

	public static void saveImageIntoMongo(File uploadImageFile,String imageFilePath,String newImageFileName) throws IOException{
		
		LOG.info("Entry :: saveImageIntoMongo");
		LOG.info("imageFilePath-->"+imageFilePath);
		LOG.info("newImageFileName-->"+newImageFileName);
		LOG.info("uploadImageFile-->"+uploadImageFile);

		//File imageFile = new File(imageFilePath);
		DB db = getMongoDBInstance("imageDb");//later on take it from the properties file instead of hardcoding
		GridFS gfsPhoto = getGridFSForImages(db,"photo");
		
		GridFSInputFile gfsFile = gfsPhoto.createFile(uploadImageFile);
		gfsFile.setFilename(newImageFileName);
		gfsFile.save();

		LOG.info("Exit :: saveImageIntoMongo");
	}

	public static GridFSDBFile getImageFileFromMongo(String imageName){

		LOG.info("Entry :: getImageFileFromMongo");

		DB db = getMongoDBInstance("imageDb");//later on take it from the properties file instead of hardcoding
		GridFS gfsPhoto = getGridFSForImages(db,"photo");
		GridFSDBFile imageForOutput=gfsPhoto.findOne(imageName);

		LOG.info("Exit :: getImageFileFromMongo");

		return imageForOutput;
	}

	public static void removeImageFromMongo(String imageName){

		LOG.info("Entry :: removeImageFromMongo");

		DB db = getMongoDBInstance("imageDb");//later on take it from the properties file insstead of hardcoding
		GridFS gfsPhoto = getGridFSForImages(db,"photo");
		gfsPhoto.remove(getImageFileFromMongo(imageName));

		LOG.info("Exit :: removeImageFromMongo");
		
	}


	public static MongoDatabase getMongoDataBaseInstance(String nameOfTheDb){

		LOG.info("Entry :: getMongoDataBaseInstance");

		MongoClient mongoClient = new MongoClient("localhost",27017);
		MongoDatabase database = mongoClient.getDatabase(nameOfTheDb);
		MongoCollection dbCol = database.getCollection("imageColl");

		LOG.info("Exit :: getMongoDataBaseInstance");

		return database;
	}



	public static void saveVideoIntoMongo(File uploadVideoFile,String videoFilePath,String newVideoFileName) throws IOException{
		
		LOG.info("Entry :: saveVideoIntoMongo");
		LOG.info("videoFilePath-->"+videoFilePath);
		LOG.info("newVideoFileName-->"+newVideoFileName);
		LOG.info("uploadVideoFile-->"+uploadVideoFile);

		//File imageFile = new File(imageFilePath);
		DB db = getMongoDBInstance("videoDb");//later on take it from the properties file instead of hardcoding
		GridFS gfsPhoto = getGridFSForImages(db,"video");
		
		GridFSInputFile gfsFile = gfsPhoto.createFile(uploadVideoFile);
		gfsFile.setFilename(newVideoFileName);
		gfsFile.save();

		LOG.info("Exit :: saveVideoIntoMongo");
	}

	public static GridFSDBFile getVideoFileFromMongo(String videoName){

		LOG.info("Entry :: getVideoFileFromMongo");

		DB db = getMongoDBInstance("videoDb");//later on take it from the properties file instead of hardcoding
		GridFS gfsPhoto = getGridFSForImages(db,"video");
		GridFSDBFile videoForOutput=gfsPhoto.findOne(videoName);

		LOG.info("Exit :: getVideoFileFromMongo");

		return videoForOutput;
	}

	public static void removeVideoFromMongo(String videoName){

		LOG.info("Entry :: removeVideoFromMongo");

		DB db = getMongoDBInstance("videoDb");//later on take it from the properties file insstead of hardcoding
		GridFS gfsPhoto = getGridFSForImages(db,"video");
		gfsPhoto.remove(getVideoFileFromMongo(videoName));

		LOG.info("Exit :: removeVideoFromMongo");
		
	}

}