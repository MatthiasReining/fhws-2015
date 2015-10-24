package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.entity.AppUser;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean
@SessionScoped
public class UserController {
    
    AppUser user;
    
    public String load(AppUser user) {
        this.user = user;
        return "user-edit";
    }
    
    public String save() {
        //TODO persist to database
        
        return "user-list";
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
    
    
}
