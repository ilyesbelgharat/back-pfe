package org.sid.services;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.entities.Photo;

import java.util.Date;
import java.util.List;

public interface AccountService {
    public AppUser saveUser(String username, String password, String confirmedPassword, String email, String lastName, String firstName, String poste, Date birthDate, Photo photo);
    public AppRole save(AppRole appRole);
    public AppUser loadUserByUsername(String username);
    public void addRoleToUser(String username, String roleName);
}
