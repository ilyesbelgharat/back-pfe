package org.sid.dao;

import org.sid.entities.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource
public interface FileRepository extends JpaRepository<File,Long> {
    public Optional<File> findById(Long id);

}
