package org.sid.Metier;



import org.sid.entities.IntervalScale;

import java.util.List;

public interface IntervalScaleMetier {
    public IntervalScale getIntervalScaleById(Long id);
    public IntervalScale saveIntervalScale(IntervalScale intervalScale);
    public void deleteIntervalScale(Long id);
    public List<IntervalScale> listIntervalScales();
    public IntervalScale updateIntervalScale(Long id, IntervalScale intervalScale);
    //existance d'une point dans un interval
    public Float existPntInterval(IntervalScale intervalScale, double point);
}
