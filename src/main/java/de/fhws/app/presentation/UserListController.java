package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.List;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class UserListController {

    final UserManagementService userManagementService = new UserManagementService();

    public List<AppUser> getAll() {
        return userManagementService.getAllUsers();
    }
}
