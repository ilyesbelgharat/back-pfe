package org.sid.services;

import com.amazonaws.services.opsworks.model.App;
import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.sid.entities.Photo;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public AccountServiceImpl(AppUserRepository appUserRepository, AppRoleRepository appRoleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
          System.out.println(username);
        System.out.println(roleName);
         
         List<AppRole> appRoles=appRoleRepository.findAll();
      //  for (int i=0;i<appRoles.size();i++){
           //     if(appRoles.get(i).getRoleName()=="USER" ){
                 //   appRoleRepository.delete(appRoles.get(i));
             //   }
      //  }
        AppRole appRole=appRoleRepository.findByRoleName(roleName);
        System.out.println(appRole);

        AppUser appUser=appUserRepository.findByUsername(username);
        System.out.println(appUser);
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public AppUser saveUser(String username, String password, String confirmedPassword, String email, String lastName, String firstName, String poste, Date birthDate, Photo photo) {
      
        
        
        AppUser user= appUserRepository.findByUsername(username);
        if(user!=null) throw new RuntimeException("User Already exists");
        if(!password.equals(confirmedPassword)) throw new RuntimeException("Please confirm your password");
        AppUser appUser=new AppUser();
        appUser.setUsername(username);
        appUser.setEmail(email);
        appUser.setUsername(username);
        appUser.setBirthDate(birthDate);
        appUser.setFirstName(firstName);
        appUser.setLastName(lastName);
            System.out.println(photo);

        appUser.setPhoto(photo);

        appUser.setActived(true);
        appUser.setPoste(poste);
        appUser.setPassword(bCryptPasswordEncoder.encode(password));
        System.out.println(appUser);
        appUserRepository.save(appUser);

        
        
        
        
        addRoleToUser(username,"USER");

        return appUserRepository.save(appUser);

    }


    @Override
    public AppRole save(AppRole appRole) {
        return appRoleRepository.save(appRole);

    }


    @Override
    public AppUser loadUserByUsername(String username) {
        return appUserRepository.findByUsername(username);

    }



}
