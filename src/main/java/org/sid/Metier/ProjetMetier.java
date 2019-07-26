package org.sid.Metier;



import org.sid.entities.*;

import java.util.List;
import java.util.Map;

public interface ProjetMetier {

    public Projet getProjetById(Long id);
    public Long saveProjet(Projet projet);
    public Long saveProjet1(Projet projet,String username);

    public void deleteProjet(Long id);
    public Projet deleteEventProjet(Long idProjet, Long idEvent);
    public List<Projet> listProjets();
    public List<EventProjet> listEventProjets(Long id);
    public Projet listImpactsProjet(Long idP);
    public void deleteIntervalFrom(Long idProjet,Long idInterval);
    public Projet addIntervalToProjet(Long idProjet, IntervalScale intervalScale);
    public Projet updateImpactScaleMinor(Long idProjet,Integer min,Integer max);
    public Projet updateImpactScaleModerate(Long idProjet,Integer min,Integer max);
    public Projet updateImpactScaleStrong(Long idProjet,Integer min, Integer max);

    public Projet newImpactScale(Long idProjet);
    public Projet saisieOvelAllSignificance(Long idProjet);
    public Map<Long,Double>  convertOvelAllSig(Map<Long,Double> overAllSignificance);
    public Projet saisieXY(Long idProjer);
    public Projet probOccurence(Long idProjet);
    public List<EventProjet> listEventAccept(Long idProjet);
    public List<EventProjet> listEventNonAccept(Long idProjet);
    public Projet updateLimitAccept(Long idProjet,double limit);

}
