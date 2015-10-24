/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.usermanagement.controller;

import de.fhws.app.business.usermanagement.entity.AppUser;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Matthias Reining
 */
public class DBMock {
    
    static List<AppUser> users = new ArrayList<>();
    
    static {
        createUser("mickey.mouse@fhws.de", "mickey", "Mickey", "Mouse");
        createUser("donald.duck@fhws.de", "donald", "Donald", "Duck");
        createUser("dagobert.duck@fhws.de", "dagobert", "Dagobert", "Duck");
    }
    
    public static void createUser(String email, String pw, String firstName, String lastName) {
        AppUser user = new AppUser();
        user.setCreated(new Date());
        user.setEmail(email);
        user.setPassword(pw);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        
        users.add(user);
    }
    
    public static List<AppUser> getAllUsers() {
        return users;
    }
    
}
