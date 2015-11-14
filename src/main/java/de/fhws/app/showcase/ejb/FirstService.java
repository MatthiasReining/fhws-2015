package de.fhws.app.showcase.ejb;

import de.fhws.app.business.usermanagement.entity.Statistics;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class FirstService {
    
    @PersistenceContext
    EntityManager em;
    
    @EJB
    SecondService ss;
    
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void s1() {
        Statistics s = new Statistics();
        s.setEvent("message von FirstService#s1");
        em.persist(s);
        
        ss.s2();
        
        System.out.println(42/0);
    }
    
}
