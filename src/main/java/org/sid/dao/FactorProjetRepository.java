package org.sid.dao;

import org.sid.entities.FactorProjet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource

public interface FactorProjetRepository  extends JpaRepository<FactorProjet,Long> {
    public Optional<FactorProjet> findById(Long id);
}
