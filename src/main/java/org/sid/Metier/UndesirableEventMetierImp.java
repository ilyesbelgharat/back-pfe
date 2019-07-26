package org.sid.Metier;


import org.sid.dao.ProjetRepository;
import org.sid.dao.UndesirableEventRepository;
import org.sid.entities.EventProjet;
import org.sid.entities.UndesirableEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Component

public class UndesirableEventMetierImp implements UndesirableEventMetier{
    @Autowired
    private UndesirableEventRepository UER;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private  ProjetMetier projetMetier;
    @Autowired
    private EventProjetMetier eventProjetMetier;
    @Override
    public UndesirableEvent getUndesirableEventById(Long id) {
        return UER.findById(id).get();
    }

    @Override
    public UndesirableEvent saveUndesirableEvent(UndesirableEvent undesirableEvent) {
        return UER.save(undesirableEvent);
    }

    @Override
    public List<UndesirableEvent> listUndesirableEvents() {
        return UER.findAll();
    }

    @Override
    public List<UndesirableEvent> listUndesirableEventsProjet(Long idProjet) {
        int i;
        int j;
        int ind;
       List<UndesirableEvent> resultat=new ArrayList<UndesirableEvent>();
       List<EventProjet> eventProjets=projetMetier.listEventProjets(idProjet);
      //  System.out.println(eventProjets);

        List<UndesirableEvent> undesirableEvents=this.listUndesirableEvents();
       // System.out.println(undesirableEvents);
       // System.out.println(eventProjets.get(0).getUndesirableEvent().getId());
       // System.out.println(undesirableEvents.get(0).getId());
        for (i=0;i<undesirableEvents.size();i++) {
            //System.out.println(i);
            ind=0;

            for (j = 0; j < eventProjets.size(); j++) {
                //System.out.println(j);
                if (undesirableEvents.get(i).getId().equals(eventProjets.get(j).getUndesirableEvent().getId())  ){
                    ind=1;
                   // resultat.add(undesirableEvents.get(i));
              }

            }
            if(ind==0){resultat.add(undesirableEvents.get(i));}

        }
       System.out.println(resultat);
        return resultat;
    }

    @Override
    public void deleteUndesirableEvent(Long id) {
        eventProjetMetier.removeAssociationUndesEvent(getUndesirableEventById(id));

        UER.delete(getUndesirableEventById(id));
    }

    @Override
    public UndesirableEvent modifierEvent(Long id, UndesirableEvent event) {
       event.setId(id);

        return UER.save(event);


    }
}
