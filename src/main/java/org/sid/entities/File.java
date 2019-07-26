package org.sid.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long id;
    private String fileName;
    private String alt;
    private String path;
    @ManyToOne
    private AppUser userGener;

    //@Temporal(value=TemporalType.TIMESTAMP)
    private String dateGener;
}
