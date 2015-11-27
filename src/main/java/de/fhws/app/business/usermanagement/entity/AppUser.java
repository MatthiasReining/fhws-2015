/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.usermanagement.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({
    @NamedQuery(name = AppUser.findAll, query = "SELECT a FROM AppUser a"),
    @NamedQuery(name = AppUser.findByEMail, query = "SELECT a FROM AppUser a WHERE a.email = :" + AppUser.paramEMail)
})
public class AppUser implements Serializable {

    public static final String findAll = "de.fhws.app.business.usermanagement.entity.findAll";
    public static final String findByEMail = "de.fhws.app.business.usermanagement.entity.findByEmail";
    public static final String paramEMail = "email";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Pattern(regexp = "\\b[\\w._%+-]+@[\\w.-]+\\.[a-z]{2,}\\b", message = "keine gültige E-Mail")
    private String email;

    private String password;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastLogin;
    private Integer loginFailed;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Size(min = 2, max = 255)
    private String firstName;
    @Size(min = 2, max = 255)
    private String lastName;

    @Digits(integer = 5, fraction = 0)
    private String zip;
    @Size(min = 2, max = 255)
    private String city;

    @OneToMany(mappedBy = "appUser", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Statistics> statistics;

    public List<Statistics> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Statistics> statistics) {
        this.statistics = statistics;
    }

    public Integer getLoginFailed() {
        return loginFailed;
    }

    public void setLoginFailed(Integer loginFailed) {
        this.loginFailed = loginFailed;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
