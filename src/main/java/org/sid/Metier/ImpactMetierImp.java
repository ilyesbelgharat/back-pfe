package org.sid.Metier;


import org.sid.dao.EventProjetRepository;
import org.sid.dao.ImpactRepository;
import org.sid.entities.EventProjet;
import org.sid.entities.Impact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component

public class ImpactMetierImp implements ImpactMetier {

    @Autowired
    private ImpactRepository IR;
    @Autowired
    private EventProjetRepository eventProjetRepository;
    @Autowired
    private EventProjetMetier eventProjetMetier;
    @Override
    public Impact getImpactById(Long id) {
        return IR.findById(id).get();
    }

    @Override
    public Impact saveImpact(Impact i) {

        IR.save(i);
        System.out.println("111111111111111111");
        System.out.println(i.getId());
        System.out.println("2222222222222222222");

        List<EventProjet> eventProjets=eventProjetRepository.findAll();
        System.out.println(eventProjets);
        System.out.println("22222222222222222222");
        System.out.println(eventProjets.size());

        for (int j=0;j<eventProjets.size();j++){
            Map<Long,Integer> hashMap=new HashMap<Long, Integer>();
            EventProjet eventProjet=eventProjets.get(j);
           // System.out.println(eventProjets.get(j));
         //   System.out.println(i.getId());
           // System.out.println(eventProjets.get(j).getEstimationImpact());
            //System.out.println("33333333333333333334444444444444444");
            System.out.println("test debut");
            System.out.println(eventProjets.get(j).getEstimationImpact());

            System.out.println("test fin");

            if(eventProjet.getEstimationImpact()==null){
                    hashMap.put(i.getId(),1);
                    eventProjets.get(j).setEstimationImpact(hashMap);

                }
              else
                {
                    System.out.println("else");

                    System.out.println(eventProjet.getEstimationImpact());

                    hashMap.putAll(eventProjet.getEstimationImpact());
                    System.out.println(hashMap);
                    hashMap.put(i.getId(),1);
                    System.out.println(hashMap);

                    eventProjet.setEstimationImpact(hashMap);
                    System.out.println(eventProjet.getEstimationImpact());
                }

            eventProjetRepository.save(eventProjet);
            System.out.println(eventProjet.getEstimationImpact());


        }



        return i;
    }

    @Override
    public List<Impact> listImpacts() {
        return IR.findAll();
    }

    @Override
    public void deleteImpact(Long id) {
            List<EventProjet> eventProjets=eventProjetRepository.findAll();
        eventProjetMetier.removeAssociationImpact(getImpactById(id));
        for (int i=0;i<eventProjets.size();i++){
            eventProjets.get(i).getEstimationImpact().remove(id);
            eventProjetRepository.save(eventProjets.get(i));

        }
        System.out.println("Fin delete");
       IR.delete(getImpactById(id));
    }

    @Override
    public Impact modifierImpact(Long id, Impact impact) {
        impact.setId(id);

        return IR.save(impact);

    }

}
