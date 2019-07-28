package org.sid.web;

import lombok.Data;
import org.sid.dao.FileRepository;
import org.sid.entities.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.entities.Photo;
import org.sid.entities.UserForm;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.sid.dao.AppRoleRepository;
import org.sid.entities.AppRole;
import java.nio.file.Paths;

import java.util.Date;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private AccountService accountService;
@Autowired
   private AppRoleRepository appRoleRepository;
 @RequestMapping(value="/register1",method= RequestMethod.POST)
    public int register1(){
                  System.out.println(Paths.get("").toAbsolutePath().toString());

Path firstPath = Paths.get("/home/music/users.txt");
    Path secondPath = Paths.get("/docs/status.txt");

    System.out.println("exists: " + Files.exists(firstPath));
    System.out.println("notExists: " + Files.notExists(firstPath));
       

                    return  1;
            }
    
    @RequestMapping(value="/register",method= RequestMethod.POST)
    public AppUser register(@RequestBody UserForm userForm){
        System.out.println(userForm);
        return accountService.saveUser(userForm.getUsername(), userForm.getPassword(), userForm.getPassword(), userForm.getEmail(), userForm.getLastName(), userForm.getFirstName(), userForm.getPoste(), userForm.getBirthDate(), userForm.getPhoto());
    }


    @RequestMapping(value = "/users/{username}", method = RequestMethod.GET)
    public AppUser getOne(@PathVariable(value = "username") String username){
        return accountService.loadUserByUsername(username);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)

    public List<AppUser> listUsers() {
        return appUserRepository.findAll();
    }

    
    @Autowired
    private FileRepository fileRepository;
    @RequestMapping(value="/users/{username}" ,method=RequestMethod.DELETE)
    public boolean supprimer(@PathVariable String username){
        System.out.println(username);
   
        appUserRepository.delete(appUserRepository.findByUsername(username));
        //utilisateurRepository.deleteById(username);
        return true;
    }


}
