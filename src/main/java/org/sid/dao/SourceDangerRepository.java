package org.sid.dao;


import org.sid.entities.SourceDanger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@CrossOrigin("*")
@RepositoryRestResource
public interface SourceDangerRepository extends JpaRepository<SourceDanger, Long> {

    public List<SourceDanger> findByCode(String code);
    @RestResource(path = "/byCode")
    public Page<SourceDanger> findByCodeContains(@Param("mc") String code, Pageable pageable);
    @RestResource(path = "/byDescription")
    public List<SourceDanger> findByDescriptionContains(@Param("mc") String description);
    @RestResource(path = "/byDescription")
    public Page<SourceDanger> findByDescriptionContains(@Param("mc") String description, Pageable pageable);
}
