package org.sid.Metier;



import org.sid.entities.Impact;
import org.sid.entities.UndesirableEvent;

import java.util.List;

public interface UndesirableEventMetier {
    public UndesirableEvent getUndesirableEventById(Long id);
    public UndesirableEvent saveUndesirableEvent(UndesirableEvent undesirableEvent);
    public List<UndesirableEvent> listUndesirableEvents();

    public List<UndesirableEvent> listUndesirableEventsProjet(Long idProjet);

    public void deleteUndesirableEvent(Long id);
    public UndesirableEvent modifierEvent(Long id, UndesirableEvent event);

}
