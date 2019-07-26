package org.sid.Metier;


import org.sid.dao.ProbabilityScaleRepository;
import org.sid.dao.ProjetRepository;
import org.sid.entities.IntervalScale;
import org.sid.entities.ProbabilityScale;
import org.sid.entities.Projet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component

public class ProbabilityScaleMetierImp implements ProbabilityScaleMetier {
    @Autowired
    private ProbabilityScaleRepository PSR;
    @Autowired
    private IntervalScaleMetier intervalScaleMetier;
    @Override
    public ProbabilityScale getProbabilityScaleById(Long id) {
        return PSR.findById(id).get();
    }
    @Autowired
    private ProjetRepository projetRepository;
    @Override
    public ProbabilityScale saveProbabilityScale(ProbabilityScale probabilityScale) {
        return PSR.save(probabilityScale);
    }

    @Override
    public void deleteProbabilityScale(Long id) {
        PSR.delete(getProbabilityScaleById(id));
    }

    @Override
    public List<ProbabilityScale> listProbabilityScales() {
        return PSR.findAll();
    }

    @Override
    public double probOccurence(Long idProjet, double point) {
        Projet projet=projetRepository.findById(idProjet).get();
        List<IntervalScale> intervalScales=projet.getProbabilityScale().getIntervalScales();
        for (int i=0;i<intervalScales.size();i++) {
            if(intervalScaleMetier.existPntInterval(intervalScales.get(i),point)>-1){
                return intervalScaleMetier.existPntInterval(intervalScales.get(i),point);
            }

        }
        return point;
    }
}
