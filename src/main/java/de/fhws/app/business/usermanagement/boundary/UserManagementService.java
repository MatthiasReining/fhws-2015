package de.fhws.app.business.usermanagement.boundary;

import de.fhws.app.business.usermanagement.controller.DBMock;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.List;

public class UserManagementService {

    public AppUser getUserByEmail(String email) {
        for (AppUser user : DBMock.getAllUsers()) {
            if (user.getEmail().equals(email))
                return user;
        }
        return null;
    }

    public List<AppUser> getAllUsers() {
        return DBMock.getAllUsers();
    }
}
