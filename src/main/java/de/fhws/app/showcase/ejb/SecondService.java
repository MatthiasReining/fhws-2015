package de.fhws.app.showcase.ejb;

import de.fhws.app.business.usermanagement.entity.Statistics;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class SecondService {

    @PersistenceContext
    EntityManager em;

    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void s2() {
        Statistics s = new Statistics();
        s.setEvent("message von SecondService#s2");
        em.persist(s);
    }
}
