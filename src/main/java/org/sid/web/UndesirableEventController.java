package org.sid.web;


import org.sid.Metier.UndesirableEventMetier;
import org.sid.dao.UndesirableEventRepository;
import org.sid.entities.Impact;
import org.sid.entities.UndesirableEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class UndesirableEventController {

    @Autowired
    private org.sid.Metier.UndesirableEventMetier UndesirableEventMetier;
    @RequestMapping(value="/undesirableEvents",method= RequestMethod.POST)
    public UndesirableEvent saveUndesirableEvent(@RequestBody UndesirableEvent UndesirableEvent){
        return UndesirableEventMetier.saveUndesirableEvent(UndesirableEvent);
    }

    @RequestMapping(value="/undesirableEvents",method=RequestMethod.GET)
    public List<UndesirableEvent> UndesirableEventList(){
        return UndesirableEventMetier.listUndesirableEvents();
    }

    @RequestMapping(value="/undesirableEvents/{id}",method=RequestMethod.DELETE)
    public void deleteUndesirableEvent(@PathVariable Long id){
        UndesirableEventMetier.deleteUndesirableEvent(id);
    }

    @RequestMapping(value="/undesirableEvents/{id}",method=RequestMethod.GET)
    public UndesirableEvent getUndesirableEventById( @PathVariable Long id){
        return UndesirableEventMetier.getUndesirableEventById(id);
    }

    @Autowired
    private UndesirableEventRepository undesirableEventRepository;

    @RequestMapping(value="/undesirableEventCode/{code}",method=RequestMethod.GET)
    public UndesirableEvent getUndesirableEventByCode( @PathVariable String code){
        return undesirableEventRepository.findByCode(code);
    }

    @RequestMapping(value="/undesirableEvents/{id}",method=RequestMethod.PUT)
    public UndesirableEvent modifierImpact(@PathVariable Long id, @RequestBody  UndesirableEvent event){
        System.out.println(event);
        return UndesirableEventMetier.modifierEvent(id,event);
    }

    @RequestMapping(value="/undesirableEventsFiltree/{idProjet}",method=RequestMethod.GET)
    public List<UndesirableEvent> listUndesirableEventsProjet(@PathVariable  Long idProjet) {
        return UndesirableEventMetier.listUndesirableEventsProjet(idProjet);
    }

}
