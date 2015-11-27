package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class UserListController implements Serializable {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserManagementService userManagementService;

    public List<AppUser> getAll() {
        return userManagementService.getAllUsers();
    }
}
