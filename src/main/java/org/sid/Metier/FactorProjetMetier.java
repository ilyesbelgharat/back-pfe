package org.sid.Metier;

import org.sid.entities.FactorProjet;
import org.sid.entities.SourceDanger;

import java.util.List;

public interface FactorProjetMetier {

    public FactorProjet getFactorProjetById(Long id);
    public FactorProjet saveFactorProjet(FactorProjet f);
    public List<FactorProjet> listFactorsProjets();
    public void deleteFactor(Long id);
    public FactorProjet addSourceToFactorProjet(Long idFactorProjet, Long idSource);
    public  FactorProjet deleteSourcesFactorProjet(Long idFactorProjet);
    public Long nbreSourceFactorProjet(Long id);
    public List<Long> listnbreSourceFactorProjet();
    public FactorProjet modifierFactorProjet(Long id, FactorProjet factorProjet);
    public FactorProjet addFactorToFactorProjet(Long idFactorProjet, Long idFactor);


    }
