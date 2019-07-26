package org.sid.dao;


import org.sid.entities.IntervalScale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource
public interface IntervalScaleRepository extends JpaRepository<IntervalScale, Long> {
    public Optional<IntervalScale> findById(Long id);

}
