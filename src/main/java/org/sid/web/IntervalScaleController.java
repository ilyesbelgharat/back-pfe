package org.sid.web;

import org.sid.Metier.IntervalScaleMetier;
import org.sid.entities.IntervalScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class IntervalScaleController {

    @Autowired
    private IntervalScaleMetier IntervalScaleMetier;


    @RequestMapping(value="/intervalScales",method= RequestMethod.POST)
    public IntervalScale saveIntervalScale(@RequestBody IntervalScale IntervalScale){
        return IntervalScaleMetier.saveIntervalScale(IntervalScale);
    }


    @RequestMapping(value="/intervalScales",method=RequestMethod.GET)
    public List<IntervalScale> IntervalScaleList(){
        return IntervalScaleMetier.listIntervalScales();
    }


    @RequestMapping(value="/intervalScales/{id}",method=RequestMethod.DELETE)
    public void deleteIntervalScale(@PathVariable  Long id){
        IntervalScaleMetier.deleteIntervalScale(id);
    }



    @RequestMapping(value="/intervalScales/{id}",method=RequestMethod.GET)
    public IntervalScale getIntervalScaleById(@PathVariable  Long id){
        return IntervalScaleMetier.getIntervalScaleById(id);
    }


    @RequestMapping(value="/intervalScales/{id}",method=RequestMethod.PUT)
    public IntervalScale UpdateInterval(@PathVariable  Long id, @RequestBody IntervalScale interval){
        return IntervalScaleMetier.updateIntervalScale(id,interval);
    }

}
