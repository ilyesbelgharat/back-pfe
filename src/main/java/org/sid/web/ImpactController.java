package org.sid.web;

import org.sid.Metier.ImpactMetier;
import org.sid.dao.ImpactRepository;
import org.sid.entities.Impact;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;



@RestController
@CrossOrigin("*")

public class ImpactController {

    @Autowired
    private ImpactMetier impactMetier;


    @RequestMapping(value="/impacts",method= RequestMethod.POST)
    public Impact saveImpact(@RequestBody Impact impact){
        System.out.println(impact);
        return impactMetier.saveImpact(impact);
    }


    @RequestMapping(value="/impacts",method=RequestMethod.GET)
    public List<Impact> ImpactList(){
        return impactMetier.listImpacts();
    }



    @RequestMapping(value="/impacts/{id}",method=RequestMethod.DELETE)
    public void deleteImpact(@PathVariable Long id){
        impactMetier.deleteImpact(id);
    }



    @RequestMapping(value="/impacts/{id}",method=RequestMethod.GET)
    public Impact getImpactById( @PathVariable Long id){
        return impactMetier.getImpactById(id);
    }


    @RequestMapping(value="/impacts/{id}",method=RequestMethod.PUT)
    public Impact modifierImpact(@PathVariable Long id,@RequestBody  Impact impact){
        System.out.println(impact);

        return impactMetier.modifierImpact(id,impact);
    }

    @Autowired
    protected ImpactRepository impactRepository;
    @RequestMapping(value="/impactCode/{code}",method=RequestMethod.GET)
    public Impact getImpactByCode( @PathVariable String code){
        return impactRepository.findByCode(code);
    }

}
