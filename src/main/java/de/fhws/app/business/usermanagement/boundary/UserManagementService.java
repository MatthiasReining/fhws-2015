package de.fhws.app.business.usermanagement.boundary;

import de.fhws.app.business.usermanagement.controller.PWManager;
import de.fhws.app.business.usermanagement.entity.AppUser;
import de.fhws.app.business.usermanagement.entity.Statistics;
import de.fhws.app.presentation.LoggedInUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Stateless
public class UserManagementService {

    @PersistenceContext
    EntityManager em;

    @Inject
    @LoggedInUser
    AppUser loggedInUser;

    @Inject
    PWManager pwManager;

    public AppUser login(String email, String password) {
        AppUser user = getUserByEmail(email);
        if (user == null)
            return null;

        if (!pwManager.checkPW(password, user.getPassword())) {
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

    public AppUser findUserById(long id) {
        return em.find(AppUser.class, id);
    }

    public AppUser save(AppUser appUser) {
        if (loggedInUser != null)
            System.out.println("data saved by " + loggedInUser.getFirstName());
        return em.merge(appUser);
    }

    public boolean changePassword(AppUser appUser, String oldPasswordClearText, String newPasswordClearText) {

        if (appUser.getPassword() != null) {
            if (!pwManager.checkPW(oldPasswordClearText, appUser.getPassword())) {
                System.out.println("altes PW falsch");
                return false;
            }
        }

        String pwHash = pwManager.createPWHash(newPasswordClearText);
        appUser.setPassword(pwHash);
        System.out.println("neues PW gesetzt");

        em.merge(appUser);
        return true;
    }
}
