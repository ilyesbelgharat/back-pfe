package org.sid.dao;


import org.sid.entities.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource

public interface ProjetRepository extends JpaRepository<Projet, Long> {
    public Optional<Projet> findById(Long id);
    public Projet findByNameProject(String nameProject);


}
