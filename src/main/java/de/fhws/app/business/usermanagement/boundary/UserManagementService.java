package de.fhws.app.business.usermanagement.boundary;

import de.fhws.app.business.usermanagement.entity.AppUser;
import de.fhws.app.business.usermanagement.entity.Statistics;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

@Stateless
public class UserManagementService {

    @PersistenceContext
    EntityManager em;

    public AppUser login(String email, String password) {
        AppUser user = getUserByEmail(email);
        if (user == null)
            return null;

        if (!user.getPassword().equals(password)) {
            Integer loginFailed = user.getLoginFailed();
            if (loginFailed == null)
                loginFailed = 0;
            loginFailed++;
            user.setLoginFailed(loginFailed);

            em.merge(user);
            return null;
        }

        user.setLastLogin(new Date());
        user.setLoginFailed(0);

        Statistics s = new Statistics();
        s.setAppUser(user);
        s.setEvent("login");
        s.setEventTime(new Date());

        List<Statistics> list = user.getStatistics();
        if (list == null) {
            list = new ArrayList<>();
            list.add(s);
            user.setStatistics(list);
        } else
            user.getStatistics().add(s);

        em.merge(user);

        return user;

    }

    private int wird_der_counter_zurueck_gerollt = 0;

    public AppUser getUserByEmail(String email) {

        try {
            wird_der_counter_zurueck_gerollt++;
            return em.createNamedQuery(AppUser.findByEMail, AppUser.class)
                    .setParameter(AppUser.paramEMail, email)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("i = " + wird_der_counter_zurueck_gerollt);
            //der counter ist 1
            return null;
        }

    }

    public List<AppUser> getAllUsers() {
        List<AppUser> result = em.createNamedQuery(AppUser.findAll).getResultList();
        return result;
    }

    public AppUser save(AppUser appUser) {
        return em.merge(appUser);
    }
}
