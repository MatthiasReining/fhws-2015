package de.fhws.app.presentation;

import de.fhws.app.business.addressmanagement.boundary.AddressService;
import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class UserController {

    AppUser user;

    AddressService addressService = new AddressService();

    @EJB
    UserManagementService userManagementService;

    public String load(AppUser user) {
        this.user = user;
        return "user-edit";
    }
    
    public String newUser() {
        this.user = new AppUser();
        return "user-edit";
    }

    public String save() {
        userManagementService.save(user);
        return "user-list";
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

}
