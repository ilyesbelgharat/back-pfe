package org.sid.Metier;

import org.sid.dao.SourceDangerRepository;
import org.sid.entities.SourceDanger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component

public class SourceDangerMetierImpl implements SourceDangerMetier {
    @Autowired
    private SourceDangerRepository SDR;
    @Override
    public SourceDanger getSourceDangerById(Long id) {
        return SDR.findById(id).get();
    }

    @Override
    public SourceDanger saveSourceDanger(SourceDanger sourceDanger) {
        return SDR.save(sourceDanger);
    }

    @Override
    public List<SourceDanger> listSourceDangers() {
        return SDR.findAll();

    }

    @Override
    public void deleteSourceDanger(Long id) {
        SDR.delete(getSourceDangerById(id));
    }

    @Override
    public SourceDanger modifierSource(Long id, SourceDanger sourceDanger) {
        sourceDanger.setId(id);
        return  SDR.save(sourceDanger);
    }
}
