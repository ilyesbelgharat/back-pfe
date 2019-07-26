package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProbabilityScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private Float max;
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE},orphanRemoval=true)
    private List<IntervalScale> intervalScales=new ArrayList<IntervalScale>();

}
