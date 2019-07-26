package org.sid.web;


import org.sid.Metier.SourceDangerMetier;
import org.sid.entities.Impact;
import org.sid.entities.SourceDanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class SourceDangerController {
    @Autowired
    private SourceDangerMetier SourceDangerMetier;
    @RequestMapping(value="/sourceDangers",method= RequestMethod.POST)
    public SourceDanger saveSourceDanger(@RequestBody SourceDanger SourceDanger){
        return SourceDangerMetier.saveSourceDanger(SourceDanger);
    }
    @RequestMapping(value="/sourceDangers",method=RequestMethod.GET)
    public List<SourceDanger> SourceDangerList(){
        return SourceDangerMetier.listSourceDangers();
    }



    @RequestMapping(value="/sourceDangers/{id}",method=RequestMethod.DELETE)
    public void deleteSourceDanger(@PathVariable Long id){
        System.out.println(id);

        SourceDangerMetier.deleteSourceDanger(id);
    }

    @RequestMapping(value="/sourceDangers/{id}",method=RequestMethod.GET)

    public SourceDanger getSourceDangerById(@PathVariable Long id){
        return SourceDangerMetier.getSourceDangerById(id);
    }

    @RequestMapping(value="/source/{id}",method=RequestMethod.PUT)
    public SourceDanger modifierImpact(@PathVariable Long id, @RequestBody  SourceDanger sourceDanger){
        System.out.println(sourceDanger);

        return SourceDangerMetier.modifierSource(id,sourceDanger);
    }

}
