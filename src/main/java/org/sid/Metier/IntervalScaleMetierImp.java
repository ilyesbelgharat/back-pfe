package org.sid.Metier;


import org.sid.dao.IntervalScaleRepository;
import org.sid.entities.IntervalScale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Component

public class IntervalScaleMetierImp implements IntervalScaleMetier {
    @Autowired
    private IntervalScaleRepository IntervalSR;
    @Override
    public IntervalScale getIntervalScaleById(Long id) {
        return IntervalSR.findById(id).get();
    }

    @Override
    public IntervalScale saveIntervalScale(IntervalScale intervalScale) {
        return IntervalSR.save(intervalScale);
    }

    @Override
    public void deleteIntervalScale(Long id) {
        IntervalSR.delete(getIntervalScaleById(id));
    }

    @Override
    public List<IntervalScale> listIntervalScales() {
        return IntervalSR.findAll();
    }

    @Override
    public IntervalScale updateIntervalScale(Long id, IntervalScale intervalScale) {
        IntervalScale intervalS=IntervalSR.findById(id).get();
        intervalS.setMax(intervalScale.getMax());
        intervalS.setMin(intervalScale.getMin());
        intervalS.setValeur(intervalScale.getValeur());
        return IntervalSR.save(intervalS);
    }

    @Override
    public Float existPntInterval(IntervalScale intervalScale, double point) {

        if(intervalScale.getMin()<=point && point<=intervalScale.getMax()){
            return intervalScale.getValeur();
        }

        return Float.valueOf(-1);

    }
}
