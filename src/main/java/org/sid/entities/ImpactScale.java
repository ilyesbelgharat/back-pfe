package org.sid.entities;

import com.sun.corba.se.impl.naming.cosnaming.InternalBindingValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.scheduling.config.IntervalTask;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Syntax.Java;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ImpactScale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    @ElementCollection
    private List<Integer> minorImpact;
    @ElementCollection
    private List<Integer> moderateImpact;
    @ElementCollection
    private List<Integer> strongImpact;


}
