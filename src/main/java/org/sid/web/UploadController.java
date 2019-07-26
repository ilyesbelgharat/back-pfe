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



 

@RestController
@CrossOrigin("*")
public class UploadController {
 
	@Autowired
	StorageService storageService;
	@Autowired
	private PhotoMetier IMI;
	List<String> files = new ArrayList<String>();
	
 
	 @RequestMapping(value="/photos",headers = "Content-Type= multipart/form-data",method=RequestMethod.POST)
	public @ResponseBody
	 Photo handleFileUpload(@RequestParam("file") MultipartFile file) {
		String s="";
		 Photo image =new Photo();

		
		
		try {
			storageService.store(file);
			files.add(file.getOriginalFilename());
			//c'est aussi prouvesoir
		   s="http://localhost:8080/image/"+file.getOriginalFilename().replace('.','/');
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
