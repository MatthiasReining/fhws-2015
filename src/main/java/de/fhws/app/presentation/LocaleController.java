package de.fhws.app.presentation;

import java.io.Serializable;
import java.util.Locale;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
@SessionScoped
public class LocaleController implements Serializable {

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
