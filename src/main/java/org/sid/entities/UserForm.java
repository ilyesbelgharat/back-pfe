package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Data
public class UserForm {
    private String username;
    private String password;
    private String confirmedPassword;
    private String lastName;
    private String email;
    private String firstName;

    private String poste;

    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private Photo photo;
}
