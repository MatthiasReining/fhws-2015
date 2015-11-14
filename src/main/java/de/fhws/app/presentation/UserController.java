package de.fhws.app.presentation;

import de.fhws.app.business.addressmanagement.boundary.AddressService;
import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean
@SessionScoped
public class UserController {

    AppUser user;

    AddressService addressService = new AddressService();

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

    UserManagementService userManagementService;

    @PostConstruct
    public void init() {
        userManagementService = new UserManagementService(em, ut);
    }

    public String load(AppUser user) {
        this.user = user;
        return "user-edit";
    }

    public String save() {
        userManagementService.save(user);
        //TODO persist to database

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
