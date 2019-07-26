package org.sid.dao;


import org.sid.entities.ImpactScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource

public interface ImpactScaleRepository extends JpaRepository<ImpactScale, Long> {
    public Optional<ImpactScale> findById(Long id);

}
