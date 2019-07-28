package org.sid.web;


import org.sid.Metier.FileMetier;
import org.sid.Metier.PhotoMetier;
import org.sid.dao.FileRepository;
import org.sid.dao.ProjetRepository;
import org.sid.entities.Photo;
import org.sid.entities.Projet;
import org.sid.services.S3Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin("*")
public class UploadController2 {
	@Autowired
	private PhotoMetier IMI;
	private Photo image;
	@Autowired
	StorageService storageService;
    List<String> files = new ArrayList<String>();



  
@RequestMapping(value ="/image/{nom}/{extension}", method = RequestMethod.GET)
public @ResponseBody void getImage(HttpServletResponse response,@PathVariable String nom,@PathVariable String extension)
        throws IOException, URISyntaxException {
	//il faut modifier ce path le jour ou tu vas déploier ton app sur un serveur

	String path="/app/upload-dir/"+nom+"."+extension;


    BufferedImage image = ImageIO
            .read(new File(path));

   // response.setContentType("image/jpg");
    OutputStream out;

    out = response.getOutputStream();
    ImageIO.write(image,extension, out);

}


	@RequestMapping(value ="/files/{nom}/{extension}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<InputStreamResource> getFile(@PathVariable String nom,@PathVariable String extension)
			throws IOException {
		//il faut modifier ce path le jour ou tu vas déploier ton app sur un serveur
			String nameFile=nom+".pdf";

		String path="upload-dir/"+nom.replace(' ','-')+"."+extension;
		File file=new File(path);
		InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
				// Content-Type
				.contentType(MediaType.valueOf("application/pdf"))
				// Contet-Length
				.contentLength(file.length()) //
				.body(resource);


	}
	
@Autowired
private ProjetRepository projetRepository;

	@RequestMapping(value="/files/{idProjet}",method=RequestMethod.GET)
	public List<org.sid.entities.File> factorList(@PathVariable Long idProjet){
		Projet projet=projetRepository.findById(idProjet).get();
		return projet.getFiles();
	}
@Autowired
private FileMetier fileMetier;
@Autowired
private FileRepository fileRepository;
	@RequestMapping(value="/files/{idProjet}/{idFile}",method=RequestMethod.DELETE)
	public void  deleteFile (@PathVariable Long idProjet,@PathVariable Long idFile){
		org.sid.entities.File file =fileRepository.findById(idFile).get();
		Projet projet=projetRepository.findById(idProjet).get();
		projet.getFiles().remove(file);
		projetRepository.save(projet);
		System.out.println(idFile);
		//fileMetier.deleteFile(idFile);
	}
	
	
	
		@Autowired
	S3Services s3Services;
	@PostMapping("/api/file/upload")
	public String uploadMultipartFile(@RequestParam("keyname") String keyName, @RequestParam("uploadfile") MultipartFile file) {
		s3Services.uploadFile(keyName, file);
		return "Upload Successfully. -> KeyName = " + keyName;
	}

	@GetMapping("/api/file/{keyname}")
	public ResponseEntity<byte[]> downloadFile(@PathVariable String keyname) {
		ByteArrayOutputStream downloadInputStream = s3Services.downloadFile(keyname);

		return ResponseEntity.ok()
				.contentType(contentType(keyname))
				.header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\"" + keyname + "\"")
				.body(downloadInputStream.toByteArray());
	}

	private MediaType contentType(String keyname) {
		String[] arr = keyname.split("\\.");
		String type = arr[arr.length-1];
		switch(type) {
			case "txt": return MediaType.TEXT_PLAIN;
			case "png": return MediaType.IMAGE_PNG;
			case "jpg": return MediaType.IMAGE_JPEG;
			case "pdf": return MediaType.APPLICATION_PDF;
			default: return MediaType.APPLICATION_OCTET_STREAM;
		}
	}


}

