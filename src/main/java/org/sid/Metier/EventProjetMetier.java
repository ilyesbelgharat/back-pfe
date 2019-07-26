package org.sid.Metier;

import org.sid.entities.*;

import java.util.List;
import java.util.Map;

public interface EventProjetMetier {

    public EventProjet getEventProjetById(Long id);
    public Projet saveEventProjet(Long idEvent, Long idProjet);
    public List<EventProjet> listEventsProjets();
    public void deleteEventProjet(Long id);
    public EventProjet modifierEventProjet(Long id, EventProjet eventProjet);
    public void addImpactToEvent(Long idEvent, List<Long> idsImpact) ;
    public EventProjet deleteImpactsFromEvent(Long idEvent);
    public EventProjet addFactorProjetToEvent(Long idEvent, Long idFactor);
    public EventProjet deleteFactorsProjetsFromEvent(Long idEvent);
    public Long saveFactorProjet(Long idFactor, Long idEvent) ;
    public FactorProjet addSourceToFactorProjet(Long idSource, Long idFactorProjet) ;

    //addFactorProjet+les sources a un event
    public  void resumeFactorProjet(Long idEvent, List<Long> longs) ;

    public int getEventProjetByImpact(Impact impact ,Long idEvent );
    //delete associations impact from event
    public void removeAssociationImpact(Impact impact);
    public EventProjet updateEventProjet(Long idEvnet, EventProjet eventProjet);

    //delete associations factor from event
    public void removeAssociationFactor(Factor factor);
    //delete associations from eventProjet
    public void removeAssociationUndesEvent(UndesirableEvent undesirableEvent);

    public EventProjet setCompArray(Long idEvent, double compArray[]);
    public double[] getCompArray(Long idEvent);
    public EventProjet methodeAHP(Long idEvent);

    public double maxValueMap(Map<Long,Integer> hm);

}
