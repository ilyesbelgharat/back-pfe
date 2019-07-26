package org.sid.Metier;



import org.sid.entities.Factor;
import org.sid.entities.SourceDanger;

import java.util.List;

public interface FactorMetier {

    public Factor getFactorById(Long id);
    public Factor saveFactor(Factor f);
    public List<Factor> listFactors();
    public void deleteFactor(Long id);
    public Factor addSourceToFactor(Long idFactor, SourceDanger sourceDanger);
    public  void deleteSourceFactor(Long idFactor,Long idSource);
    public Long nbreSource(Long id);
    public List<Long> listnbreSource();
    public Factor modifierFactor(Long id, Factor factor);


}
