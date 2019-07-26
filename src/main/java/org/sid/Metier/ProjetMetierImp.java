package org.sid.Metier;
import java.text.DecimalFormat;


import org.sid.dao.*;
import org.sid.entities.*;
import org.sid.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;

import java.util.*;

@Component

public class ProjetMetierImp implements ProjetMetier {

    @Autowired
    private ProjetRepository ProjetR;
    @Autowired
    private EventProjetMetier eventProjetMetier;
    @Autowired
    private ProbabilityScaleMetier probabilityScaleMetier;
    @Autowired

    private EventProjetRepository eventProjetRepository;
    @Autowired

    private IntervalScaleMetier intervalScaleMetier;
    @Autowired

    private AccountService accountService;
    @Autowired
    private    ImpactScaleMetier impactScaleMetier;
    @Autowired
    private FactorRepository factorRepository;
@Autowired
private FactorProjetRepository factorProjetRepository;

    @Override
    public Projet getProjetById(Long id) {
        return ProjetR.findById(id).get();


    }

    @Override
    public Long saveProjet(Projet projet) {
       Long id=   ProjetR.save(projet).getId();
       projet.setLimitAccept(2);
       ProjetR.save(projet);
            System.out.println(id);
         //   this.newImpactScale(id);
            return  id;
    }

    @Override
    public Long saveProjet1(Projet projet, String username) {
        System.out.println("debuuuuuuut");
        ProjetR.save(projet);
        System.out.println("dfinnnn");
        Long id=   projet.getId();
        System.out.println(username);
        System.out.println(projet);
        AppUser appUser=accountService.loadUserByUsername(username);
        System.out.println(appUser);
        this.newImpactScale(id);
        projet.setUserCreation(appUser);
        System.out.println(projet);
     //   projet.set
        ProjetR.save(projet);
        return id;
    }

    @Override
    public void deleteProjet(Long id) {
        ProjetR.delete(getProjetById(id));
    }

    @Override
    public Projet deleteEventProjet(Long idProjet, Long idEvent) {
       Projet projet= ProjetR.findById(idProjet).get();
       projet.getEventProjets().remove(eventProjetRepository.findById(idEvent).get());
      return ProjetR.save(projet);
    }

    @Override
    public List<Projet> listProjets() {
        return ProjetR.findAll();
    }

    @Override
    public List<EventProjet> listEventProjets(Long id) {
        return ProjetR.findById(id).get().getEventProjets();


    }

    @Override
    public Projet listImpactsProjet(Long idP) {
        return ProjetR.findById(idP).get();
    }

    @Override
    public void deleteIntervalFrom(Long idProjet, Long idInterval) {
        Projet projet=this.getProjetById(idProjet);
        IntervalScale intervalScale=intervalScaleMetier.getIntervalScaleById(idInterval);
        for(int i=0;i<projet.getProbabilityScale().getIntervalScales().size();i++){
            if(projet.getProbabilityScale().getIntervalScales().get(i).getId()==idInterval){
                projet.getProbabilityScale().getIntervalScales().remove(intervalScale);
                ProjetR.save(projet);
                return;
            }

        }

    }
    public Projet addIntervalToProjet(Long idProjet, IntervalScale intervalScale){
        Projet projet=this.getProjetById(idProjet);
        IntervalScale intervalScale1=intervalScaleMetier.saveIntervalScale(intervalScale);
        if(projet.getProbabilityScale()==null){
            ProbabilityScale probabilityScale=new ProbabilityScale();
            probabilityScale.getIntervalScales().add(intervalScale);
            probabilityScaleMetier.saveProbabilityScale(probabilityScale);
            System.out.println(probabilityScale);

            projet.setProbabilityScale(probabilityScale);
            ProjetR.save(projet);

        }
        else{
            projet.getProbabilityScale().getIntervalScales().add(intervalScale);
            ProjetR.save(projet);

        }
        return  projet;
    }

    @Override
    public Projet updateImpactScaleMinor(Long idProjet, Integer min, Integer max) {
        List<Integer> list=new ArrayList<Integer>();
        for(int i=min ;i<= max;i++) {
            list.add(i);
        }
        System.out.println(list);
        Projet projet=this.getProjetById(idProjet);
        projet.getImpactScale().setMinorImpact(list);
        return ProjetR.save(projet);


    }

    @Override
    public Projet updateImpactScaleModerate(Long idProjet, Integer min, Integer max) {
        List<Integer> list=new ArrayList<Integer>();
        for(int i=min ;i<= max;i++) {
            list.add(i);
        }
        System.out.println(list);
        Projet projet=this.getProjetById(idProjet);
        projet.getImpactScale().setModerateImpact(list);
        return ProjetR.save(projet);

    }

    @Override
    public Projet updateImpactScaleStrong(Long idProjet, Integer min, Integer max) {
        List<Integer> list=new ArrayList<Integer>();
        for(int i=min ;i<=max;i++) {
            list.add(i);
        }
        System.out.println(list);
        Projet projet=this.getProjetById(idProjet);
        projet.getImpactScale().setStrongImpact(list);
        return ProjetR.save(projet);
    }


    public Projet newImpactScale(Long idProjet) {
        ImpactScale impactScale=new ImpactScale();
        List<Integer> minor=new ArrayList<Integer>();
        minor.add(1);
        minor.add(2);
        minor.add(3);
        List<Integer> moderate=new ArrayList<Integer>();
        moderate.add(4);
        moderate.add(5);
        moderate.add(6);
        List<Integer> strong=new ArrayList<Integer>();
        strong.add(7);
        strong.add(8);
        strong.add(9);
        impactScale.setMinorImpact(minor);
        impactScale.setModerateImpact(moderate);
        impactScale.setStrongImpact(strong);
impactScaleMetier.saveImpactScale(impactScale);

Projet projet=this.getProjetById(idProjet);

projet.setImpactScale(impactScale);
return ProjetR.save(projet);

    }

    @Override
    public Map<Long,Double>  convertOvelAllSig(Map<Long,Double> overAllSignificance){
        Map<Long,Double> overAllSignificanceRank= overAllSignificance
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));
        List<Long> result = new ArrayList(overAllSignificanceRank.keySet());
        List<Double> result1 = new ArrayList(overAllSignificanceRank.values());
        Map<Long,Double> overAllSignificance1=new HashMap<Long, Double>();
for (int i=0;i<result.size();i++){
    double l= i+1;
    overAllSignificance1.put(result.get(i),l);
}
return  overAllSignificance1;
    }

    @Override
    public Projet saisieXY(Long idProjer) {
        Projet projet=ProjetR.findById(idProjer).get();
        System.out.println(projet);
        List<EventProjet> eventProjets=projet.getEventProjets();
        System.out.println(eventProjets.size());
        double sumTotal=0;
        for(int i=0;i<eventProjets.size();i++){
            System.out.println("-----------------------------------");

            System.out.println(i);
            EventProjet eventProjet=eventProjets.get(i);
            List<FactorProjet> factorProjets=eventProjet.getFactorProjets();
            double sumEvent=0;
            System.out.println(factorProjets.size());
            for(int j=0;j<factorProjets.size();j++) {
                System.out.println("-");
                FactorProjet factorProjet=factorProjets.get(j);
                System.out.println("-");

                Long idFactor=factorProjet.getFactor().getId();
                System.out.println("-");

                double xy=projet.getOverAllSignificanceRank().get(idFactor)*factorProjet.getSourceDangers().size();
                System.out.println("-");
System.out.println(xy);
                sumEvent=sumEvent+xy;
                System.out.println("--1--");
System.out.println(factorProjet.getId());
                factorProjet.setXY(xy);
                System.out.println("-1");

                factorProjetRepository.save(factorProjet);
                System.out.println("-");

            }
            System.out.println(sumEvent);
            sumTotal=sumTotal+sumEvent;
                    eventProjet.setXY(sumEvent);
                    eventProjetRepository.save(eventProjet);
            }
        projet.setXY(sumTotal);
         ProjetR.save(projet);

for (int i=0;i<eventProjets.size();i++) {
    EventProjet eventProjet=eventProjets.get(i);
    double fraction=eventProjet.getXY()/projet.getXY();
    eventProjet.setFractionTotal((float) fraction);
    eventProjetRepository.save(eventProjet);

}



        return ProjetR.save(projet);
    }

    // set proba occurence + max impact d'un projet
    @Override
    public Projet probOccurence(Long idProjet) {
        Projet projet=ProjetR.findById(idProjet).get();
        List<EventProjet> eventProjets=projet.getEventProjets();
        for (int i=0;i<eventProjets.size();i++) {
            EventProjet eventProjet=eventProjets.get(i);
            double point=this.probabilityScaleMetier.probOccurence(idProjet,eventProjet.getFractionTotal());
            eventProjet.setProbabilityOccurence((float) point);
            Map<Long,Integer> map= eventProjet.getEstimationImpact();
            double max=eventProjetMetier.maxValueMap(map);
            System.out.println(max);
            eventProjet.setMaxImpact(max);
            eventProjet.setLevelRisk((float) (max*point));
            eventProjetRepository.save(eventProjet);
        }

        return ProjetR.save(projet);
    }


    @Override
    public List<EventProjet> listEventAccept(Long idProjet) {
        Projet projet=ProjetR.findById(idProjet).get();
        double limit=projet.getLimitAccept();
                List<EventProjet> eventProjets= projet.getEventProjets();
    List<EventProjet> eventProjets1=new ArrayList<EventProjet>();
                for (int i=0;i<eventProjets.size();i++){
                    EventProjet eventProjet=eventProjets.get(i);
                    if(eventProjet.getLevelRisk()<=limit){
                        eventProjets1.add(eventProjet);
                    }

                }
            return eventProjets1;
    }

    @Override
    public List<EventProjet> listEventNonAccept(Long idProjet) {
        Projet projet=ProjetR.findById(idProjet).get();
        double limit=projet.getLimitAccept();
        List<EventProjet> eventProjets= projet.getEventProjets();
        List<EventProjet> eventProjets1=new ArrayList<EventProjet>();
        for (int i=0;i<eventProjets.size();i++){
            EventProjet eventProjet=eventProjets.get(i);
            if(eventProjet.getLevelRisk()>limit){
                eventProjets1.add(eventProjet);
            }

        }
        return eventProjets1;

    }

    @Override
    public Projet updateLimitAccept(Long idProjet,double limit) {
        Projet projet=ProjetR.findById(idProjet).get();
        projet.setLimitAccept(limit);
        return ProjetR.save(projet);
    }

    @Override
    public Projet saisieOvelAllSignificance(Long idProjet) {
        Map<Long,Double> overAllSignificance = new HashMap<Long, Double>();
        Projet projet=ProjetR.findById(idProjet).get();
        List<Factor> factors= factorRepository.findAll();
        for (int j = 0; j < factors.size(); j++) {
            double resultat=1;
            for (int i=0;i<projet.getEventProjets().size();i++) {
            resultat=resultat*projet.getEventProjets().get(i).getWeights()[j];
                System.out.println(resultat);
            }
            overAllSignificance.put(factors.get(j).getId(),Math.sqrt(resultat));
        }
            projet.setOverAllSignificance(overAllSignificance);
            ProjetR.save(projet);
        projet.setOverAllSignificanceRank(this.convertOvelAllSig(overAllSignificance));
        ProjetR.save(projet);
        this.saisieXY(idProjet);
        ProjetR.save(projet);
            this.probOccurence(idProjet);

        return ProjetR.save(projet);
    }

}
