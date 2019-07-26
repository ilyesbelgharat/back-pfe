package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@NoArgsConstructor  @AllArgsConstructor
@ToString()
public class Impact {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;

    private String code;
    private String description;

    //@ManyToMany(mappedBy = "impacts")
   // private List<EventProjet> eventProjets=new ArrayList<EventProjet>();

}
