package de.fhws.app.business.usermanagement.boundary;

import de.fhws.app.business.logmanager.entity.LogInfo;
import de.fhws.app.business.usermanagement.controller.DBMock;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
}
