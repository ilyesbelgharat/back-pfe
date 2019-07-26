package org.sid.dao;



import org.sid.entities.Impact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RepositoryRestResource
public interface ImpactRepository extends JpaRepository<Impact, Long> {

    public Optional<Impact> findById(Long id);

    public Impact findByCode(String code);

    @Query("SELECT i FROM Impact i")
    List<Impact> findImpacts();





}
