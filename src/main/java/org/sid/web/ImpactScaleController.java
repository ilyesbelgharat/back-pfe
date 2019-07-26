package org.sid.web;


import org.sid.Metier.ImpactScaleMetier;
import org.sid.entities.ImpactScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ImpactScaleController {
    @Autowired
    private ImpactScaleMetier ImpactScaleMetier;
    @RequestMapping(value="/impactScales",method= RequestMethod.POST)
    public ImpactScale saveImpactScale(@RequestBody  ImpactScale ImpactScale){
        return ImpactScaleMetier.saveImpactScale(ImpactScale);
    }
    @RequestMapping(value="/impactScales",method=RequestMethod.GET)
    public List<ImpactScale> ImpactScaleList(){
        return ImpactScaleMetier.listImpactScales();
    }

    @RequestMapping(value="/impactScales/{id}",method=RequestMethod.DELETE)
    public void deleteImpactScale(Long id){
        ImpactScaleMetier.deleteImpactScale(id);
    }
    @RequestMapping(value="/impactScales/{id}",method=RequestMethod.GET)

    public ImpactScale getImpactScaleById(Long id){
        return ImpactScaleMetier.getImpactScaleById(id);
    }
}
