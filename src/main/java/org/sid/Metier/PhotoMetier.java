package org.sid.Metier;



import org.sid.entities.Photo;
import org.sid.entities.Projet;

import java.util.List;

public interface PhotoMetier {
    public Photo savePhoto(Photo c);
    public Photo getPhotobyid(Long id);
    public List<Photo> listePhoto();
    public void deletePhoto(Long id);
}
