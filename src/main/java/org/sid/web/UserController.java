package org.sid.web;

import lombok.Data;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppUser;
import org.sid.entities.Photo;
import org.sid.entities.UserForm;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

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
    public AppUser register1(){
        
         System.out.println(userForm);
        AppRole appRole=new AppRole();
        appRole.setRoleName("ADMIN");
        appRoleRepository.save(appRole);
        AppRole appRole1=new AppRole();
        appRole.setRoleName("USER");
        appRoleRepository.save(appRole1);
        return accountService.saveUser("admin1", "1234", "1234", " " , " ", " ", " ", null, null);
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

    @RequestMapping(value="/users/{username}" ,method=RequestMethod.DELETE)
    public boolean supprimer(@PathVariable String username){
        System.out.println(username);
        appUserRepository.delete(appUserRepository.findByUsername(username));
        //utilisateurRepository.deleteById(username);
        return true;
    }


}
