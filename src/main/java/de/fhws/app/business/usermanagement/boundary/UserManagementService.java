package de.fhws.app.business.usermanagement.boundary;

import de.fhws.app.business.logmanager.entity.LogInfo;
import de.fhws.app.business.usermanagement.controller.DBMock;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

public class UserManagementService {

    EntityManager em;
    UserTransaction ut;

    public UserManagementService(EntityManager em, UserTransaction ut) {
        this.em = em;
        this.ut = ut;
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

        try {
            ut.begin();

            appUser = em.merge(appUser);

            ut.commit();

            return appUser;
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            throw new RuntimeException(ex);
        }

    }
}
