package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

@ManagedBean
public class LoginController {

    private String email;
    private String password;
    
    final UserManagementService userManagementService = new UserManagementService();
    
    //TODO: search for correct solution
    //@ManagedProperty(value="#{localeController}")
    //LocaleController localeController;
    
    public String login() {
        
        System.out.println(email);
        System.out.println(password);
        
        AppUser user = userManagementService.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            user.setLastLogin(new Date());
            //TODO pw passt nicht
            return "user-list.xhtml?faces-redirect=true";
        }
        
        
        //FIXME please use language form localeController
        Locale locale = Locale.ENGLISH;
        String msg = ResourceBundle.getBundle("messages", locale).getString("loginFailed");
        FacesMessage fm = new FacesMessage(msg);
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
