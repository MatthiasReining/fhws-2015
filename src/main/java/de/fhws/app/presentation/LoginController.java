package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LoginController implements Serializable{

    private String email;
    private String password;

    @EJB
    UserManagementService userManagementService;

    public String login() {

        System.out.println(email);
        System.out.println(password);

        AppUser user = userManagementService.login(email, password);
        if (user != null)
            return "user-list.xhtml?faces-redirect=true";

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
