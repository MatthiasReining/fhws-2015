package de.fhws.app.presentation;

import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;


@ManagedBean
@SessionScoped
public class LocaleController {
    
    private String lang;
    
    @PostConstruct
    void init() {
        Locale locale = FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
        lang = locale.getLanguage();
        System.out.println("Initiale Language: " + lang);        
    }
    
    public void change(String lang) {
        this.lang = lang;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
    
    
}
