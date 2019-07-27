package org.sid.Metier;

import org.sid.dao.FileRepository;
import org.sid.dao.ProjetRepository;
import org.sid.entities.File;
import org.sid.entities.Projet;
import org.sid.web.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
@Component

public class FileMetierImpl implements FileMetier {
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    StorageService storageService;
    List<String> files = new ArrayList<String>();
@Autowired
private ProjetRepository projetRepository;
    @Override
    public File saveFile(File file) {
        return fileRepository.save(file);   }

    @Override
    public File getFilebyid(Long id) {
        return fileRepository.findById(id).get();
    }

    @Override
    public List<File> listFiles() {
        return fileRepository.findAll();
    }

    @Override
    public void deleteFile(Long id) {
        File file=getFilebyid(id);

        fileRepository.delete(file);
    }


    @Override
    public File handleFileUpload( MultipartFile file,Long idProjet) {
        String s="";
        File f =new File();
        Projet projet=projetRepository.findById(idProjet).get();


        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());
            //c'est aussi prouvesoir
            s="http://ilyesapprisk/herokuapp/com/files/"+file.getOriginalFilename().replace('.','/').replace(' ','-');
            //s=file.getOriginalFilename();
            f.setPath("pathhh");
f.setFileName(file.getOriginalFilename());

            fileRepository.save(f);

            projet.getFiles().add(f);
            projetRepository.save(projet);

            return f;




        } catch (Exception e) {
            return f;

        }
    }
}
