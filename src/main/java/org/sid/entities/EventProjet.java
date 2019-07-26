package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString()

public class EventProjet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    @ManyToOne
    private UndesirableEvent undesirableEvent;

    @ManyToMany
   // @JoinColumn
    private List<Impact> impacts;

    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    private List<FactorProjet> factorProjets;

   @ElementCollection
    private Map<Long,Integer> estimationImpact=new HashMap<Long, Integer>();

   //@ElementCollection

   @OrderColumn (name="mtx_id")
    private double mtx[][];
    private double compArray[];
   //   @OrderColumn (name="weight-id")

    private double weights[];


    private double XY;
    private Float fractionTotal;
    private Float probabilityOccurence;
    private Float levelRisk;
    private double maxImpact;


}
