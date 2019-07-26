package org.sid.Metier;

import org.sid.entities.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileMetier {

    public File saveFile(File file);
    public File getFilebyid(Long id);
    public List<File> listFiles();
    public void deleteFile(Long id);
    //
    public File handleFileUpload( MultipartFile file,Long idProjet);

    }
