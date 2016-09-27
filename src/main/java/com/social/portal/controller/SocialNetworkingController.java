package com.social.portal.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.net.URL;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import org.apache.log4j.Logger;
import org.bson.Document;


import com.social.portal.bean.Post;
import com.social.portal.bean.User;
import com.social.portal.service.SocialNetworkingService;
import com.social.portal.bean.LoginForm;
import com.social.portal.util.MongoUtility;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import com.mongodb.gridfs.GridFSDBFile;


@Controller
public class SocialNetworkingController{
	private static final Logger LOG = Logger.getLogger(SocialNetworkingController.class);
	private static final String IMAGE_FROM_PATH = "F:/MyWorldImages/ImagesFromMongo/";
	private static final String VIDEO_FROM_PATH = "F:/MyWorldVideos/VideosFromMongo/";

	@Autowired
	private SocialNetworkingService socialNetworkingService;

	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String loginPage(Map model,HttpServletRequest request,HttpServletResponse response){
		LOG.info("Inside loginPage");
		LoginForm loginForm = new LoginForm();
		model.put("loginForm",loginForm);
		return "login";
	}

	@RequestMapping(value="/loginSubmit",method=RequestMethod.POST)
	public String loginSubmit(HttpServletRequest request,@ModelAttribute LoginForm loginForm,HttpServletResponse response){
		LOG.info("Entry :: loginSubmit");

		LOG.info("Entry :: loginSubmit");
		return "redirect:/fetch-public-post.do";
	}

	@RequestMapping(value="/register",method=RequestMethod.GET)
	public String registerUser(Map model,HttpServletRequest request,HttpServletResponse response){
		LOG.info("Entry :: registerUser");
		User user = new User();
		model.put("user",user);
		LOG.info("Exit :: registerUser");
		return "user-registration";
	}

	@RequestMapping(value="/add-user",method=RequestMethod.POST)
	public String addUser(Map model,HttpServletRequest request,@ModelAttribute User user,HttpServletResponse response){
		LOG.info("Entry :: addUser");
		socialNetworkingService.addUser(user);
		model.put("userSuccess","User added successfully");
		LOG.info("Entry :: addUser");
		return "home";
	}

	@RequestMapping(value="/remove-user",method=RequestMethod.POST)
	public String removeUser(Map model,HttpServletRequest request,@ModelAttribute User user,HttpServletResponse response){
		LOG.info("Entry :: removeUser in the controller");
		socialNetworkingService.removeUser(user);
		model.put("userRemoved","User removed successfully");
		LOG.info("Exit :: removeUser in the controller");
		return "user-form";
	}

	@RequestMapping(value="/load-post-home",method=RequestMethod.GET)
	public String loadPostForm(Map model,Post post,HttpServletRequest request){
		LOG.info("Entry :: loadPostForm in the controller");
		List<String> postTypes = new ArrayList<String>();
		postTypes.add("Public");//Hardcoded for testing purpose,later on should be populated from properties file
		postTypes.add("Private");//Hardcoded for testing purpose,later on should be populated from properties file
		model.put("post",post);
		model.put("postTypes",postTypes);
		LOG.info("Exit :: loadPostForm in the controller");
		return "post-form";
	}

	@RequestMapping(value="/add-post",method=RequestMethod.POST)
	public String addPost(Map model,HttpServletRequest request,@ModelAttribute Post post,@RequestParam CommonsMultipartFile[] imagePost,HttpServletResponse response,@RequestParam CommonsMultipartFile[] videoPost){
		LOG.info("Entry :: addPost in the controller");
		try{
			socialNetworkingService.addPost(post,imagePost,videoPost);
			model.put("postSuccess","Post added successfully");
		}
		catch(IOException e){
			LOG.error("Error :: while adding new Post",e);
		}
		LOG.info("Exit :: addPost in the controller");
		return "redirect:/fetch-public-post.do";
	}

	@RequestMapping(value="/fetch-public-post",method=RequestMethod.GET)
	public String fetchPosts(Map model,HttpServletRequest request,HttpServletResponse response){
		LOG.info("Entry :: fetchPosts");
		List<Document> publicPosts = new ArrayList<Document>();
		publicPosts=socialNetworkingService.fetchPublicPosts();
		model.put("publicPosts",publicPosts);
		LOG.info("End :: fetchPosts");
		return "home";
	}

	@RequestMapping(value="/remove-post",method=RequestMethod.POST)
	public String deletePost(Map model,HttpServletRequest request,@ModelAttribute Post post,HttpServletResponse response){
		LOG.info("Entry :: deletePost in the controller");
		socialNetworkingService.deletePost(post);
		model.put("postDeleted","Post removed successfully");
		LOG.info("Exit :: deletePost in the controller");
		return "home";
	}


	@RequestMapping(value="/imageController/{imageName}",produces = { MediaType.IMAGE_JPEG_VALUE,MediaType.IMAGE_GIF_VALUE, MediaType.IMAGE_PNG_VALUE })
	@ResponseBody
	public byte[] getImageForPost(@PathVariable String imageName){
		LOG.info("Entry :: getImageForPost"); 
		BufferedImage bufferedImageForPost = null;
		URL resourcePath = null;
		//byte[] imageArray =new byte[4096];
		byte[] imageArray = null;
		final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		try{
			headers.setContentType(MediaType.IMAGE_JPEG);
			LOG.info("imageName-->"+imageName);
			File imageFromMongo = new File(IMAGE_FROM_PATH+imageName);
			GridFSDBFile imageFile=MongoUtility.getImageFileFromMongo(imageName);
			imageFile.writeTo(imageFromMongo);
			bufferedImageForPost=ImageIO.read(imageFromMongo);
			//imageFile.getInputStream().read(imageArray);
			imageFile.getInputStream();


			/*File file = new File("resources/img/bg_smartphones.jpg");
			String absolutePath = file.getAbsolutePath();
			LOG.info("resources image path "+absolutePath);
			resourcePath=this.getClass().getResource("resources/img/bg_smartphones.jpg");
			LOG.info("URL approach "+resourcePath);*/

			imageArray = new byte[(int)imageFile.getLength()];
			imageFile.getInputStream().read(imageArray);


		 //imageArray = ;

      


		}
		catch(IOException e){
			LOG.error("Error :: while fetching image from the GridFs post",e);
		}
				
		LOG.info("Exit :: getImageForPost");
		//return bufferedImageForPost.getData();
		return imageArray;
	}

	@RequestMapping(value="/videoController/{videoObj}",produces = "video/mp4")
	@ResponseBody
	public ResponseEntity<byte[]> getVideoForPost(@PathVariable String videoObj,HttpServletResponse response){
		LOG.info("Entry :: getVideoForPost"); 
		BufferedImage bufferedVideoForPost = null;
		URL resourcePath = null;
		//byte[] imageArray =new byte[4096];
		byte[] videoArray = null;
		ResponseEntity<byte[]> result = null;
		//final org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders();
		try{
			//headers.setContentType(MediaType.IMAGE_JPEG);
			LOG.info("videoObj-->"+videoObj);
			File videoFromMongo = new File(VIDEO_FROM_PATH+videoObj);
			GridFSDBFile videoFile=MongoUtility.getVideoFileFromMongo(videoObj);
			videoFile.writeTo(videoFromMongo);
			bufferedVideoForPost=ImageIO.read(videoFromMongo);
			//imageFile.getInputStream().read(imageArray);
			videoFile.getInputStream();


			HttpHeaders headers = new HttpHeaders();
	        //headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.setContentLength((int)videoFile.getLength());
	       

	       /* response.setContentLength(videoArray.length);
	        response.setContentType("video/mp4");*/

			/*File file = new File("resources/img/bg_smartphones.jpg");
			String absolutePath = file.getAbsolutePath();
			LOG.info("resources image path "+absolutePath);
			resourcePath=this.getClass().getResource("resources/img/bg_smartphones.jpg");
			LOG.info("URL approach "+resourcePath);*/

			videoArray = new byte[(int)videoFile.getLength()];

			 result = new ResponseEntity<byte[]>(videoArray, headers, HttpStatus.OK);
			videoFile.getInputStream().read(videoArray);

			LOG.info("videoArray-->"+videoArray);
			LOG.info("videoArray size-->"+videoArray.length);
		 //imageArray = ;

      


		}
		catch(IOException e){
			LOG.error("Error :: while fetching image from the GridFs post",e);
		}
				
//		LOG.info("Exit :: getVideoForPost");
		//return bufferedImageForPost.getData();
		return result;
	}

}