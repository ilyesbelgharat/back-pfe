package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factor  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String code;
    private String description;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    private Collection<SourceDanger> sourceDangers= new ArrayList<>();

}
