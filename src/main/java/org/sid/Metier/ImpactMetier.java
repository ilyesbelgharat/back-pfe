package org.sid.Metier;



import org.sid.entities.Impact;

import java.util.List;

public interface ImpactMetier {

    public Impact getImpactById(Long id);
    public Impact saveImpact(Impact i);
    public List<Impact> listImpacts();
    public void deleteImpact(Long id);
    public Impact modifierImpact(Long id, Impact impact);



}
