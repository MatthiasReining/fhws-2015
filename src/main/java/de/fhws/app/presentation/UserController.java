package de.fhws.app.presentation;

import de.fhws.app.business.addressmanagement.boundary.AddressService;
import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class UserController implements Serializable {

    AppUser user = new AppUser();

    AddressService addressService = new AddressService();

    private String oldPassword;
    private String newPassword;

    @EJB
    UserManagementService userManagementService;

    public String load(AppUser user) {
        this.user = user;
        return "user-edit";
    }

    public String newUser() {
        oldPassword = "";
        newPassword = "";
        this.user = new AppUser();
        return "user-edit";
    }

    public String save() {
        userManagementService.save(user);
        return "user-list";
    }

    public String changePassword() {

        System.out.println("old pw: " + oldPassword);
        System.out.println("new pw: " + newPassword);

        boolean changed = userManagementService.changePassword(user, oldPassword, newPassword);

        oldPassword = "";
        newPassword = "";
        if (changed)
            return "user-list";
        else
            return "user-edit";
    }

    public void loadCity() {
        String city = addressService.getCityByZip(user.getZip());
        user.setCity(city);
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

}
