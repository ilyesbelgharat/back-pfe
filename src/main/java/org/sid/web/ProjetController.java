package org.sid.web;


import org.sid.Metier.ProjetMetier;
import org.sid.dao.EventProjetRepository;
import org.sid.entities.EventProjet;
import org.sid.entities.IntervalScale;
import org.sid.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class ProjetController {
    @Autowired
    private ProjetMetier ProjetMetier;
    @Autowired
    private EventProjetRepository eventProjetRepository;

    @RequestMapping(value="/projets",method= RequestMethod.POST)
    public Long saveProjet(@RequestBody  Projet Projet){
        for (int i=0;i<Projet.getEventProjets().size();i++) {
            EventProjet eventProjet = Projet.getEventProjets().get(i);
            eventProjetRepository.save(eventProjet);
            System.out.println(eventProjet.getEstimationImpact());
        }
        return ProjetMetier.saveProjet(Projet);
    }


    @RequestMapping(value="/projets/{username}",method= RequestMethod.POST)
    public Long saveProjet1(@PathVariable String username,@RequestBody  Projet Projet){
        System.out.println(username);
        System.out.println(Projet);
        return ProjetMetier.saveProjet1(Projet,username);
    }

    @RequestMapping(value="/projets",method=RequestMethod.GET)
    public List<Projet> ProjetList(){
        return ProjetMetier.listProjets();
    }

    @RequestMapping(value="/projets/{id}",method=RequestMethod.DELETE)
    public void deleteProjet(@PathVariable Long id){
        ProjetMetier.deleteProjet(id);
    }
    @RequestMapping(value="/projets/{id}",method=RequestMethod.GET)

    public Projet getProjetById(@PathVariable Long id){
        return ProjetMetier.getProjetById(id);
    }

    @RequestMapping(value="/projets/{id}/eventsProjet",method=RequestMethod.GET)
    public List<EventProjet> getListEventsProjet(@PathVariable Long id){
        return ProjetMetier.listEventProjets(id);

    }

    @RequestMapping(value="/projets/{idProjet}/events/{idEvent}",method=RequestMethod.PUT)
    public Projet deleteEventProjet(@PathVariable Long idProjet,@PathVariable Long idEvent){
        return ProjetMetier.deleteEventProjet(idProjet,idEvent);
    }
    @RequestMapping(value="/projets/{idProjet}/interval/{idInterval}",method=RequestMethod.PUT)
    public int deleteInterval(@PathVariable Long idProjet,@PathVariable Long idInterval){
        System.out.println(idProjet);
        System.out.println(idInterval);
            ProjetMetier.deleteIntervalFrom(idProjet,idInterval);
            return 1;
    }
    @RequestMapping(value="/projets/{idProjet}/interval",method=RequestMethod.PUT)

    public Projet addIntervalToProjet(@PathVariable  Long idProjet,@RequestBody IntervalScale intervalScale){
        return ProjetMetier.addIntervalToProjet(idProjet,intervalScale);
    }

    @RequestMapping(value="/projets/{idProjet}/impactScaleMinor",method=RequestMethod.PUT)

    public Projet updateImpactScaleMinor(@PathVariable  Long idProjet,@RequestBody List<Integer> integers){
        return ProjetMetier.updateImpactScaleMinor(idProjet,integers.get(0),integers.get(1));
    }
    @RequestMapping(value="/projets/{idProjet}/impactScaleModerate",method=RequestMethod.PUT)

    public Projet updateImpactScaleModerate(@PathVariable  Long idProjet,@RequestBody List<Integer> integers){
        return ProjetMetier.updateImpactScaleModerate(idProjet,integers.get(0),integers.get(1));
    }


    @RequestMapping(value="/projets/{idProjet}/impactScaleStrong",method=RequestMethod.PUT)
    public Projet updateImpactScaleStrong(@PathVariable  Long idProjet,@RequestBody List<Integer> integers){
        return ProjetMetier.updateImpactScaleStrong(idProjet,integers.get(0),integers.get(1));
    }

    @RequestMapping(value="/projets/{idProjet}/concentration",method=RequestMethod.PUT)
    public Projet saisieOvelAllSignificance(@PathVariable  Long idProjet) {

        return ProjetMetier.saisieOvelAllSignificance(idProjet);
    }


    @RequestMapping(value="/projets/{idProjet}/eventsAccept",method=RequestMethod.GET)
    public List<EventProjet> listEventAccept(@PathVariable Long idProjet) {
        return ProjetMetier.listEventAccept(idProjet);
    }
    @RequestMapping(value="/projets/{idProjet}/eventsNonAccept",method=RequestMethod.GET)
    public List<EventProjet> listEventNonAccept(@PathVariable Long idProjet) {
        return ProjetMetier.listEventNonAccept(idProjet);
    }

    @RequestMapping(value="/projets/{idProjet}/limitAccept/{limit}",method=RequestMethod.PUT)
    public Projet updateLimitAccept(@PathVariable  Long idProjet,@PathVariable Double limit) {
System.out.println("abir");
        return ProjetMetier.updateLimitAccept(idProjet,limit);
    }


    @RequestMapping(value="/Listprojets",method=RequestMethod.GET)
    public List<Projet> listProjets() {
        return ProjetMetier.listProjets();
    }




    }
