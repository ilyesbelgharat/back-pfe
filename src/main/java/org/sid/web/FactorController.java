package org.sid.web;


import org.sid.Metier.FactorMetier;
import org.sid.dao.FactorRepository;
import org.sid.dao.SourceDangerRepository;
import org.sid.entities.Factor;
import org.sid.entities.SourceDanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class FactorController {

    @Autowired
    private FactorMetier factorMetier;

    @RequestMapping(value="/factors",method= RequestMethod.POST)
    public Factor saveFactor(@RequestBody Factor factor){
        return factorMetier.saveFactor(factor);
    }

    @RequestMapping(value="/factors",method=RequestMethod.GET)
    public List<Factor> factorList(){
        return factorMetier.listFactors();
    }

    @RequestMapping(value="/factors/{id}",method=RequestMethod.DELETE)
    public void deleteFactor(@PathVariable Long id){
        factorMetier.deleteFactor(id);
    }

    @RequestMapping(value="/factors/{id}",method=RequestMethod.GET)
    public Factor getFactorById(@PathVariable Long id){
        return factorMetier.getFactorById(id);
    }

    @RequestMapping(value="/factors/{idFactor}/source",method= RequestMethod.POST)
    public Factor addSourceToFactor(@PathVariable Long idFactor, @RequestBody SourceDanger sourceDanger){
        System.out.println(idFactor);
      return factorMetier.addSourceToFactor(idFactor,sourceDanger);
    }

    @RequestMapping(value="/factors/{idFactor}/source",method=RequestMethod.GET)
    public List<SourceDanger> listSourceFactor(@PathVariable Long idFactor){

        return (List<SourceDanger>) factorMetier.getFactorById(idFactor).getSourceDangers();
    }


    @RequestMapping(value="/factors/{idFactor}/source/{idSource}",method=RequestMethod.DELETE)
    public void deleteSourceFactor(@PathVariable Long idFactor, @PathVariable Long idSource) {


        factorMetier.deleteSourceFactor(idFactor,idSource);



    }


    @RequestMapping(value="/factors/{idFactor}/nbreSource",method=RequestMethod.GET)
    public Long nbreSource(@PathVariable Long idFactor){
        return factorMetier.nbreSource(idFactor);
    }

    @RequestMapping(value="/factors/nbresource",method=RequestMethod.GET)
    public  List<Long> nbresource(){
        return factorMetier.listnbreSource();
    }


    @RequestMapping(value="/factors/{id}",method=RequestMethod.PUT)
    public Factor modifierFactor(@PathVariable Long id,@RequestBody  Factor factor){
        System.out.println(factor);

        return factorMetier.modifierFactor(id,factor);
    }


    @Autowired
    private FactorRepository factorRepository;

    @Autowired
    private SourceDangerRepository sourceDangerRepository;

    @RequestMapping(value="/factor/{code}",method=RequestMethod.GET)
    public Factor getFactorByCode(@PathVariable String code){
        return factorRepository.findByCode(code);

    }


    @RequestMapping(value="/factor/{idFactor}/{codeSource}",method=RequestMethod.GET)
    public SourceDanger getSourceByCode(@PathVariable Long idFactor,@PathVariable String codeSource){

                Factor factor=factorRepository.findById(idFactor).get();
                List<SourceDanger> sourceDangers=(List<SourceDanger>)factor.getSourceDangers();
                for(int i=0;i<sourceDangers.size();i++){
                    System.out.println("resultat");
                    System.out.println(codeSource);
                    System.out.println(sourceDangers.get(i).getCode());
                    System.out.println("resultat");

                    if(sourceDangers.get(i).getCode().equals(codeSource)){
                        System.out.println(sourceDangers.get(i));
                        return sourceDangers.get(i);
                    }


                }
        return null;


    }



}
