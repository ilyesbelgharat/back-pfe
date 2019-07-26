package org.sid.dao;


import org.sid.entities.EventProjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource
public interface EventProjetRepository extends JpaRepository<EventProjet,Long> {

    public Optional<EventProjet> findById(Long id);
}
