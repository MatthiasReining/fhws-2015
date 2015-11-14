package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ManagedBean
public class UserListController {

    @PersistenceContext
    EntityManager em;

    @EJB
    UserManagementService userManagementService;

    public List<AppUser> getAll() {
        return userManagementService.getAllUsers();
    }
}
