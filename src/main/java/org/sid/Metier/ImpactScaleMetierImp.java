package org.sid.Metier;

import org.sid.dao.ImpactScaleRepository;
import org.sid.entities.ImpactScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component

public class ImpactScaleMetierImp implements ImpactScaleMetier {
    @Autowired
    private ImpactScaleRepository ISR;
    @Override
    public ImpactScale getImpactScaleById(Long id) {
        return ISR.findById(id).get();
    }

    @Override
    public ImpactScale saveImpactScale(ImpactScale impactScale) {
        return ISR.save(impactScale);
    }
    @Override
    public void deleteImpactScale(Long id){
        ISR.delete(getImpactScaleById(id));
    }

    @Override
    public ImpactScale updateImpactScale(Long id, ImpactScale impactScale) {
        ImpactScale is=ISR.findById(id).get();
        is.setMinorImpact(impactScale.getMinorImpact());
        is.setModerateImpact(impactScale.getModerateImpact());
        is.setStrongImpact(impactScale.getStrongImpact());
        return ISR.save(is);

    }

    @Override
    public List<ImpactScale> listImpactScales() {
        return ISR.findAll();
    }
}
