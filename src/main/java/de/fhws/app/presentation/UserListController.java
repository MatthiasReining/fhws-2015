package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean
public class UserListController {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

    UserManagementService userManagementService;

    @PostConstruct
    public void init() {
        userManagementService = new UserManagementService(em, ut);
    }

    public List<AppUser> getAll() {
        return userManagementService.getAllUsers();
    }
}
