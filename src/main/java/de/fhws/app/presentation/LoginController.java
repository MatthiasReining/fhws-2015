package de.fhws.app.presentation;

import de.fhws.app.business.usermanagement.boundary.UserManagementService;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

@ManagedBean
public class LoginController {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

    private String email;
    private String password;

    UserManagementService userManagementService;

    @PostConstruct
    public void init() {
        userManagementService = new UserManagementService(em, ut);
    }

    //TODO: search for correct solution
    //@ManagedProperty(value="#{localeController}")
    //LocaleController localeController;
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
