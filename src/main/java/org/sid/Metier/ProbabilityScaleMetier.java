package org.sid.Metier;



import org.sid.entities.ProbabilityScale;

import java.util.List;

public interface ProbabilityScaleMetier {
    public ProbabilityScale getProbabilityScaleById(Long id);
    public ProbabilityScale saveProbabilityScale(ProbabilityScale probabilityScale);
    public void deleteProbabilityScale(Long id);
    public List<ProbabilityScale> listProbabilityScales();
    //return occurence de probabilite du point d"un projet idProjet
    public double probOccurence(Long idProjet,double point);
}
