package org.sid.Metier;



import org.sid.entities.SourceDanger;

import java.util.List;

public interface SourceDangerMetier {
    public SourceDanger getSourceDangerById(Long id);
    public SourceDanger saveSourceDanger(SourceDanger sourceDanger);
    public List<SourceDanger> listSourceDangers();
    public void deleteSourceDanger(Long id);
    public SourceDanger modifierSource(Long id, SourceDanger sourceDanger);
}
