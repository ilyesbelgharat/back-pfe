package org.sid.web;


import org.sid.Metier.EventProjetMetier;
import org.sid.entities.EventProjet;
import org.sid.entities.Impact;
import org.sid.entities.Projet;
import org.sid.entities.UndesirableEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin("*")
public class EventProjetController {

    @Autowired
    private EventProjetMetier eventProjetMetier;


    @RequestMapping(value="/eventProjet/{idProjet}/{idEvent}",method= RequestMethod.POST)
    public Projet saveEventProjet(@PathVariable Long idEvent, @PathVariable Long idProjet){
        System.out.println(idEvent);
        System.out.println(idProjet);
        return eventProjetMetier.saveEventProjet(idEvent,idProjet);

    }

    @RequestMapping(value="/eventProjetSave/{idProjet}",method= RequestMethod.POST)
    public void saveEventProjet1(@PathVariable Long idProjet, @RequestBody List<Long> idsEvent) {
        for (int i = 0; i < idsEvent.size(); i++) {
            eventProjetMetier.saveEventProjet(idsEvent.get(i), idProjet);

        }

    }


    @RequestMapping(value="/eventProjetimpact/{idEvent}",method= RequestMethod.PUT)
    public void addImpactToEvent(@PathVariable Long idEvent, @RequestBody List<Long> idsImpact) {
       eventProjetMetier.addImpactToEvent(idEvent,idsImpact);
    }


    @RequestMapping(value="/eventProjet/{idEvent}",method= RequestMethod.PUT)
    public EventProjet deleteImpactsFromEvent(@PathVariable Long idEvent) {
        return eventProjetMetier.deleteImpactsFromEvent(idEvent);
    }

//ajoutet factor + list sources a un event
    @RequestMapping(value="/factorProjet/{idEvent}",method= RequestMethod.POST)
    public void resumeFactorProjet (@PathVariable Long idEvent,@RequestBody List<Long> longs) {
        System.out.println("000");

        System.out.println(longs);
        System.out.println("000");

        eventProjetMetier.resumeFactorProjet(idEvent,longs);

    }

    @RequestMapping(value="/factorProjet1/{idEvent}",method= RequestMethod.POST)
    public void resumeFactorProjet1 (@PathVariable Long idEvent,@RequestBody List<List<Long>> longs) {
        System.out.println("000");

        System.out.println(longs);
        System.out.println("000");
                for (int i=0;i<longs.size();i++) {

                    eventProjetMetier.resumeFactorProjet(idEvent,longs.get(i));
                }
    }




    @RequestMapping(value="/factorProjet/{idEvent}",method= RequestMethod.PUT)
    public EventProjet deleteFactorsFromEvent(@PathVariable Long idEvent) {
        return eventProjetMetier.deleteFactorsProjetsFromEvent(idEvent);
    }


    @RequestMapping(value="/eventProjetCompArray/{idEvent}",method= RequestMethod.PUT)
    public void setCompArray(@PathVariable Long idEvent, @RequestBody double compArray[]) {
        System.out.println("c bn");
        eventProjetMetier.setCompArray(idEvent,compArray);
    }

    @RequestMapping(value="/eventProjetCompArray/{idEvent}",method= RequestMethod.GET)
    public double[] getCompArray(@PathVariable Long idEvent) {
        System.out.println("c bn");
       return eventProjetMetier.getCompArray(idEvent);
    }

    @RequestMapping(value="/eventProjetAHP/{idEvent}",method= RequestMethod.PUT)
    public EventProjet methodeAHP(@PathVariable  Long idEvent){
        System.out.println("rrrrr");
            return  eventProjetMetier.methodeAHP(idEvent);
    }


}
