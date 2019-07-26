package org.sid.dao;

import org.sid.entities.UndesirableEvent;
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
public interface UndesirableEventRepository extends JpaRepository<UndesirableEvent, Long> {

    public UndesirableEvent findByCode(String code);
    @RestResource(path = "/byCode")
    public Page<UndesirableEvent> findByCodeContains(@Param("mc") String code, Pageable pageable);
    @RestResource(path = "/byDescription")
    public List<UndesirableEvent> findByDescriptionContains(@Param("mc") String description);
    @RestResource(path = "/byDescriptionPage")
    public Page<UndesirableEvent> findByDescriptionContains(@Param("mc") String description, Pageable pageable);
}
