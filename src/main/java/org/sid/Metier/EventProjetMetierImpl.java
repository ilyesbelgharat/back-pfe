package org.sid.Metier;

import org.sid.dao.*;
import org.sid.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Map;
@Component
public class EventProjetMetierImpl implements EventProjetMetier {
    @Autowired
    private EventProjetRepository eventProjetRepository;
    @Autowired
    private UndesirableEventRepository undesirableEventRepository;
    @Autowired
    private ProjetRepository projetRepository;
    @Autowired
    private ImpactRepository impactRepository;
    @Autowired
    private FactorRepository factorRepository;
    @Autowired
    private SourceDangerRepository sourceDangerRepository;
    @Autowired
    private FactorProjetRepository factorProjetRepository;
    @Override
    public EventProjet getEventProjetById(Long id) {
        return eventProjetRepository.findById(id).get();
    }
@Autowired
private ProjetMetier projetMetier;






    @Override
    public Projet saveEventProjet(Long idEvent, Long idProjet) {
        List<Impact> impacts=impactRepository.findAll();
        int n=factorRepository.findAll().size();
        int nbreFactors=(n-1)*n/2;
        System.out.println(nbreFactors);

        double comp[]=new double[nbreFactors];
        UndesirableEvent undesirableEvent=undesirableEventRepository.findById(idEvent).get();
        Projet projet = projetRepository.findById(idProjet).get();
        EventProjet ev=new EventProjet();

        ev.setFractionTotal((float) 0);
        ev.setLevelRisk((float) 0);
ev.setProbabilityOccurence((float) 0);
ev.setMaxImpact((float) 1);
ev.setXY((float) 0);
        eventProjetRepository.save(ev);
        ev.setUndesirableEvent(undesirableEvent);
        for (int i=0;i<impacts.size();i++) {
            ev.getEstimationImpact().put(impacts.get(i).getId(),1);
        }
for(int i=0;i<nbreFactors;i++){
comp[i]=1;
}
System.out.println(comp);
ev.setCompArray(comp);

double mtx[][]=new double[n][n];
int t=0;
for (int a=0;a<n;a++){
    for (int b=0;b<n;b++) {
        if(a>=b){mtx[a][b]=1;}
            else {

            mtx[a][b]=comp[t];
            t++;
        }
        }

    }
ev.setMtx(mtx);




        eventProjetRepository.save(ev);

       projet.getEventProjets().add(ev);


      return projetRepository.save(projet);

    }


    @Override
    public Long saveFactorProjet(Long idFactor, Long idEvent) {

        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
        Factor factor = factorRepository.findById(idFactor).get();

        FactorProjet factorProjet=new FactorProjet();
        factorProjetRepository.save(factorProjet);
        factorProjet.setFactor(factor);
        factorProjetRepository.save(factorProjet);

        eventProjet.getFactorProjets().add(factorProjet);
      eventProjetRepository.save(eventProjet);
        return factorProjet.getId();

    }











    @Override
    public List<EventProjet> listEventsProjets() {
        return null;
    }


    @Override
    public void deleteEventProjet(Long id) {
        eventProjetRepository.delete(eventProjetRepository.findById(id).get());
    }


    @Override
    public EventProjet modifierEventProjet(Long id, EventProjet eventProjet) {
        eventProjet.setId(id);

        return eventProjetRepository.save(eventProjet);

    }

    @Override
    public void addImpactToEvent(Long idEvent, List<Long> idsImpact) {
        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
    for (int i=0;i<idsImpact.size();i++){
        Impact impact=impactRepository.findById(idsImpact.get(i)).get();

        eventProjet.getImpacts().add(impact);
      eventProjetRepository.save(eventProjet);
       // System.out.println(eventProjet.getImpacts());

    }


    }

    @Override
    public EventProjet deleteImpactsFromEvent(Long idEvent) {
        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
        eventProjet.getImpacts().clear();
        return  eventProjetRepository.save(eventProjet);
    }

    @Override
    public EventProjet addFactorProjetToEvent(Long idEvent, Long idFactorProjet) {
        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
        FactorProjet factorProjet=factorProjetRepository.findById(idFactorProjet).get();

        eventProjet.getFactorProjets().add(factorProjet);
        return eventProjetRepository.save(eventProjet);


    }

    @Override
    public FactorProjet addSourceToFactorProjet(Long idSource, Long idFactorProjet) {
       // System.out.println(idFactorProjet);
        //System.out.println(idSource);

        SourceDanger sourceDanger=sourceDangerRepository.findById(idSource).get();
       // System.out.println(sourceDanger);

        FactorProjet factorProjet=factorProjetRepository.findById(idFactorProjet).get();
       // System.out.println(factorProjet);

        factorProjet.getSourceDangers().add(sourceDanger);
       // System.out.println(factorProjet.getSourceDangers());

        return factorProjetRepository.save(factorProjet);

    }

    @Override
    public void resumeFactorProjet(Long idEvent, List<Long> longs) {

        Long idFactorProjet = this.saveFactorProjet(longs.get(0), idEvent);


        for (int i = 1; i < longs.size(); i++) {
            this.addSourceToFactorProjet(longs.get(i),idFactorProjet);
        }


    }

    @Override
    public int getEventProjetByImpact(Impact impact ,Long idEvent ) {
        EventProjet eventProjet = eventProjetRepository.findById(idEvent).get();

        for (int i = 0; i < eventProjet.getImpacts().size(); i++) {
            if (eventProjet.getImpacts().get(i).getId()==impact.getId()){
                return i;
            }

        }
        return -1;
    }

    @Override
    public EventProjet deleteFactorsProjetsFromEvent(Long idEvent) {
        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
        eventProjet.getFactorProjets().clear();
        return eventProjetRepository.save(eventProjet);


    }
    public void removeAssociationImpact(Impact impact){
        int ind;
        List<EventProjet> eventProjets=eventProjetRepository.findAll();
        System.out.println(eventProjets);
        System.out.println(eventProjets.size());

        for (int i=0;i<eventProjets.size();i++){
            ind=this.getEventProjetByImpact(impact,eventProjets.get(i).getId());
            System.out.println(ind);

            if(ind != -1){
                EventProjet eventProjet=eventProjets.get(i);
               // EventProjet eventProjet1=new EventProjet();
                System.out.println(eventProjet.getId());
                System.out.println(impact.getId());
                ///////////////////////////////////////////////////////////
                //eventProjet1.setUndesirableEvent(eventProjet.getUndesirableEvent());
                //eventProjet1.setImpacts(eventProjet.getImpacts());
               // eventProjet1.setFactorProjets(eventProjets.get);
                /////////////////////////////////////////////////////////////
                eventProjet.getImpacts().remove(impact);
                System.out.println(eventProjet);


               eventProjetRepository.save(eventProjet);
              //  System.out.println("houniiiiiiiiiiiiii");

            }

        }

    }

    @Override
    public EventProjet updateEventProjet(Long idEvnet, EventProjet eventProjet) {
        eventProjet.setId(idEvnet);
        return eventProjetRepository.save(eventProjet);

    }

    @Override
    public void removeAssociationFactor(Factor factor) {
        System.out.println("D");

        List<EventProjet> eventProjets=eventProjetRepository.findAll();
        for (int i=0;i<eventProjets.size();i++) {

            for (int j=0;j<eventProjets.get(i).getFactorProjets().size();j++) {
                if (eventProjets.get(i).getFactorProjets().get(j).getFactor().getId() == factor.getId()) {
                        EventProjet eventProjet=eventProjets.get(i);
                        //Factor factor1=eventProjets.get(i).getFactorProjets().get(j).getFactor();

                        FactorProjet factorProjet=eventProjets.get(i).getFactorProjets().get(j);
                    System.out.println("Avantcleaaaaaaar");

                    factorProjet.getSourceDangers().clear();

                        System.out.println("cleaaaaaaar");
                        factorProjet.setFactor(null);
                        factorProjetRepository.save(factorProjet);
                        eventProjet.getFactorProjets().remove(factorProjet);
                        eventProjetRepository.save(eventProjet);

                }
            }

        }




        System.out.println("D2");

        List<FactorProjet> factorProjets=factorProjetRepository.findAll();
        for(int i=0;i<factorProjets.size();i++){
            System.out.println(i);
            if(factorProjets.get(i).getFactor() != null){
            if(factorProjets.get(i).getFactor().getId()==factor.getId()){
                System.out.println(factorProjets.get(i).getFactor().getId());
                System.out.println(factor.getId());
                factorProjets.get(i).getSourceDangers().clear();
                factorProjets.get(i).setFactor(null);
                factorProjetRepository.save(factorProjets.get(i));
            }
        }}
        System.out.println("finnnnnn");

        factor.getSourceDangers().clear();
        System.out.println("finnnnnn---");

        factorRepository.save(factor);
        System.out.println("finnnnnnfinn");


    }

    @Override
    public void removeAssociationUndesEvent(UndesirableEvent undesirableEvent) {
        //System.out.println(undesirableEvent);

        List<Projet> projets=projetRepository.findAll();
        List<EventProjet> eventProjets=eventProjetRepository.findAll();
        //System.out.println(projets);

        for (int i=0;i<eventProjets.size();i++) {
            //System.out.println(eventProjets);
           // System.out.println("22222222222222222222222");
           // System.out.println(eventProjets.get(i).getUndesirableEvent().getId());
           // System.out.println(undesirableEvent.getId());
if(eventProjets.get(i).getUndesirableEvent() != null){
            if(eventProjets.get(i).getUndesirableEvent().getId()==undesirableEvent.getId()) {
               // System.out.println("3333333333333333333333");

                EventProjet eventProjet=eventProjets.get(i);
                    eventProjet.setUndesirableEvent(null);
                    eventProjet.getFactorProjets().clear();
                    eventProjet.getImpacts().clear();
                    eventProjetRepository.save(eventProjet);

                   for (int j=0;j<projets.size();j++) {
                       for (int n=0;n<projets.get(j).getEventProjets().size();n++) {
                           if(projets.get(j).getEventProjets().get(n).getId()==eventProjet.getId()) {
                               Projet projet=projets.get(j);
                               projet.getEventProjets().remove(eventProjet);
                               projetRepository.save(projet);

                           }

                       }
                        //eventProjetRepository.delete(eventProjet);
                    }
              }}
          //  System.out.println("4444444444444444444");


        }
      //  System.out.println("hhhhhhhhhhhhhhhhh");

     }

    @Override
    public EventProjet setCompArray(Long idEvent, double[] compArray) {
        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
        eventProjet.setCompArray(compArray);
        int n=factorRepository.findAll().size();
        double mtx[][]=new double[n][n];
        int t=0;
        for (int a=0;a<n;a++){
            for (int b=0;b<n;b++) {
                if(a>=b){mtx[a][b]=1;}
                else {
                    System.out.println(compArray[t]);
                    mtx[a][b]=compArray[t];
                    t++;
                }
            }
        }

        eventProjet.setMtx(mtx);
        eventProjetRepository.save(eventProjet);
        this.methodeAHP(idEvent);
      //  Long l= Long.valueOf(7);
        //Projet projet=projetRepository.findById(l).get();
        //System.out.println(projet.getOverAllSignificance());

        //System.out.println(projetMetier.convertOvelAllSig(projet.getOverAllSignificance()));
        return eventProjet;
    }

    @Override
    public EventProjet methodeAHP(Long idEvent) {
        EventProjet eventProjet=eventProjetRepository.findById(idEvent).get();
        List<Factor> factors=factorRepository.findAll();

        Long labels[]=new Long[factors.size()];

        for (int i=0;i<factors.size();i++) {
            labels[i] = factors.get(i).getId();
        }


        AHP ahp = new AHP(factors.size());
        double compArray[] = ahp.getPairwiseComparisonArray();
        //Saisie comArray
        compArray=eventProjet.getCompArray();

        ahp.setPairwiseComparisonArray(compArray);
        eventProjet.setWeights(ahp.getWeights());
        int n=factors.size();

        for(int i=0;i<n;i++) {
            System.out.println(ahp.getWeights()[i]);
        }

        return eventProjetRepository.save(eventProjet);


    }

    @Override
    public double maxValueMap(Map<Long, Integer> hm) {
        Map.Entry<Long,Integer> firstEntry = hm.entrySet().iterator().next();
        Long largestKey = firstEntry.getKey();
        Integer largestKeyValue = firstEntry.getValue();

        for (Map.Entry<Long, Integer> map : hm.entrySet()) {
            Integer val = map.getValue();
            if (val > largestKeyValue) {
                largestKeyValue = val;
               // largestKeyValue = map.getValue();
            }
        }
return largestKeyValue;
    }

    @Override
    public double[] getCompArray(Long idEvent){
        return eventProjetRepository.findById(idEvent).get().getCompArray();
    }


}
