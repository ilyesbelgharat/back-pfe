package org.sid.dao;


import org.sid.entities.ProbabilityScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource

public interface ProbabilityScaleRepository extends JpaRepository<ProbabilityScale, Long> {
   public Optional<ProbabilityScale> findById(Long id);
}
