package org.sid.web;


import org.sid.Metier.ProbabilityScaleMetier;
import org.sid.entities.ProbabilityScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProbabilityScaleController {
    @Autowired
    private ProbabilityScaleMetier ProbabilityScaleMetier;
    @RequestMapping(value="/probabilityScales",method= RequestMethod.POST)
    public ProbabilityScale saveProbabilityScale(ProbabilityScale ProbabilityScale){
        return ProbabilityScaleMetier.saveProbabilityScale(ProbabilityScale);
    }
    @RequestMapping(value="/probabilityScales",method=RequestMethod.GET)
    public List<ProbabilityScale> ProbabilityScaleList(){
        return ProbabilityScaleMetier.listProbabilityScales();
    }

    @RequestMapping(value="/probabilityScales/{id}",method=RequestMethod.DELETE)
    public void deleteProbabilityScale(Long id){
        ProbabilityScaleMetier.deleteProbabilityScale(id);
    }
    @RequestMapping(value="/probabilityScales/{id}",method=RequestMethod.GET)

    public ProbabilityScale getProbabilityScaleById(Long id){
        return ProbabilityScaleMetier.getProbabilityScaleById(id);
    }
}
