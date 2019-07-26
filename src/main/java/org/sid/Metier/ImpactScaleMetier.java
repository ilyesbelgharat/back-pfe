package org.sid.Metier;



import org.sid.entities.ImpactScale;

import java.util.List;

public interface ImpactScaleMetier {

    public ImpactScale getImpactScaleById(Long id);
    public ImpactScale saveImpactScale(ImpactScale impactScale);
    public void deleteImpactScale(Long id);
    public ImpactScale  updateImpactScale(Long id, ImpactScale impactScale);
    public List<ImpactScale> listImpactScales();

}
