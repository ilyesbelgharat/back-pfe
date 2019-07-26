package org.sid.Metier;


import org.sid.dao.PhotoRepository;
import org.sid.entities.Photo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component

public class PhotoMetierImp implements PhotoMetier {
    @Autowired
    private PhotoRepository PR;
    @Override
    public Photo savePhoto(Photo c) {
        return PR.save(c);
    }

    @Override
    public Photo getPhotobyid(Long id) {
        return PR.findById(id).get();
    }

    @Override
    public List<Photo> listePhoto() {
        return PR.findAll();
    }

    @Override
    public void deletePhoto(Long id) {
        PR.delete(getPhotobyid(id));
    }
}
