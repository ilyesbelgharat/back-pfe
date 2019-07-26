package org.sid.Metier;

import org.sid.dao.FactorProjetRepository;
import org.sid.dao.FactorRepository;
import org.sid.dao.SourceDangerRepository;
import org.sid.entities.Factor;
import org.sid.entities.FactorProjet;
import org.sid.entities.SourceDanger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FactorProjetMetierImpl implements FactorProjetMetier {
    @Autowired
    private FactorProjetRepository factorProjetRepository;
    @Autowired
    private SourceDangerRepository sourceDangerRepository;
    @Autowired
    private FactorRepository factorRepository;


    @Override
    public FactorProjet getFactorProjetById(Long id) {
        return factorProjetRepository.findById(id).get();
    }

    @Override
    public FactorProjet saveFactorProjet(FactorProjet f) {

        return factorProjetRepository.save(f);
    }

    @Override
    public List<FactorProjet> listFactorsProjets() {

        return factorProjetRepository.findAll();

    }

    @Override
    public void deleteFactor(Long id) {
         factorProjetRepository.delete(getFactorProjetById(id));

    }

    @Override
    public FactorProjet addSourceToFactorProjet(Long idFactorProjet, Long idSource) {
        FactorProjet factorProjet=factorProjetRepository.findById(idFactorProjet).get();
        SourceDanger sourceDanger=sourceDangerRepository.findById(idSource).get();
        factorProjet.getSourceDangers().add(sourceDanger);
        return factorProjetRepository.save(factorProjet);
    }


    @Override
    public FactorProjet addFactorToFactorProjet(Long idFactorProjet, Long idFactor) {
        FactorProjet factorProjet=factorProjetRepository.findById(idFactorProjet).get();
        Factor factor=factorRepository.findById(idFactor).get();
        factorProjet.setFactor(factor);
        return factorProjetRepository.save(factorProjet);
    }

    @Override
    public FactorProjet deleteSourcesFactorProjet(Long idFactorProjet) {
        FactorProjet factorProjet=factorProjetRepository.findById(idFactorProjet).get();
        factorProjet.getSourceDangers().clear();

        return factorProjetRepository.save(factorProjet);



    }

    @Override
    public Long nbreSourceFactorProjet(Long id) {
        return null;
    }

    @Override
    public List<Long> listnbreSourceFactorProjet() {
        return null;
    }

    @Override
    public FactorProjet modifierFactorProjet(Long id, FactorProjet factorProjet) {
        return null;
    }
}
