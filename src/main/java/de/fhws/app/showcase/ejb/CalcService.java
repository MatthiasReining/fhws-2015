package de.fhws.app.showcase.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CalcService {
    
    @PersistenceContext
    EntityManager em;

    public int calculation() {
        return 42;
    }
}
