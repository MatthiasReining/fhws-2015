package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.controller.DBMock;
import de.fhws.app.business.usermanagement.entity.AppUser;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

@ManagedBean
public class LoginController {

    private String email;
    private String password;
    
    final UserManagementService userManagementService = new UserManagementService();
    
    public String login() {
        
        System.out.println(email);
        System.out.println(password);
        
        AppUser user = userManagementService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            //TODO pw passt nicht
            return "user-list.xhtml?faces-redirect=true";
        }
        
        
        FacesMessage fm = new FacesMessage("Login fehlgeschlagen");
        FacesContext.getCurrentInstance().addMessage(null, fm);
        return "login";
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
}
