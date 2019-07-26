package org.sid.web;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


import org.sid.Metier.PhotoMetier;
import org.sid.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@CrossOrigin("*")
public class PhotoController{
    private static String UPLOADED_FOLDER = "src/main/resources/static/upload";
    private static String UPLOADED_FOLDER2="/upload/" ;
	@Autowired
	private PhotoMetier PM;




	    @RequestMapping(value="/photos",method=RequestMethod.GET)
		public List<Photo> listePhoto() {
			return PM.listePhoto();
		}



	    @RequestMapping(value="/photos/{id}",method=RequestMethod.GET)
		public Photo getPhotobyid(@PathVariable Long id) {
	    	//le @path pour dire ce /id à quoi referencie
			return PM.getPhotobyid(id);
		}


	    @RequestMapping(value="/photos/{id}",method=RequestMethod.DELETE)
		public void DeletePhoto(@PathVariable Long id) {
	    	PM.deletePhoto(id);
		}


}

