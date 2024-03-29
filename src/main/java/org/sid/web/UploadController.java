package org.sid.web;

import java.util.ArrayList;
import java.util.List;


import org.sid.Metier.PhotoMetier;
import org.sid.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.sid.services.S3Services;



 

@RestController
@CrossOrigin("*")
public class UploadController {
 
	@Autowired
	StorageService storageService;
	@Autowired
	private PhotoMetier IMI;
	List<String> files = new ArrayList<String>();
	@Autowired
	private S3Services s3Services;
 
	 @RequestMapping(value="/photos",headers = "Content-Type= multipart/form-data",method=RequestMethod.POST)
	public @ResponseBody
	 Photo handleFileUpload(@RequestParam("file") MultipartFile file) {
		String s="";
		String s1="";
		 Photo image =new Photo();

		
		
		try {
			s3Services.uploadFile(file.getOriginalFilename(), file);
			files.add(file.getOriginalFilename());
			//c'est aussi prouvesoir
		   s1="https://ilyesapprisk.herokuapp.com/image/";
	   
	           s=s1+file.getOriginalFilename().replace('.','/');
			
			//s=file.getOriginalFilename();
			image.setPath(s);
			System.out.println("imaaaaaaaaaaaaaaaa");
			System.out.println(image);
			System.out.println("imaaaaaaaaaaaaaaaa");

			IMI.savePhoto(image);
			return image; 
			
			
		} catch (Exception e) {
			return image;
			
					}
	}
 
	
}
