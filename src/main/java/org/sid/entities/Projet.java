package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Projet {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String nameProject;

    private String departement;
    private String type;

    @ManyToOne
    private AppUser userCreation;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    List<EventProjet> eventProjets;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    List<File> files;

    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    private ImpactScale impactScale;
    @OneToOne(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    private ProbabilityScale probabilityScale=new ProbabilityScale();

    @ElementCollection
    private Map<Long,Double> overAllSignificance = new HashMap<Long, Double>();

    @ElementCollection
    private Map<Long,Double> overAllSignificanceRank = new HashMap<Long, Double>();

    private double XY;

    private double limitAccept;

}
