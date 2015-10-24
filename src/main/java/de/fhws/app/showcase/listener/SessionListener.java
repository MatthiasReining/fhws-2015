/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.listener;

import de.fhws.app.business.usermanagement.entity.AppUser;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("session created");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        AppUser user = (AppUser) se.getSession().getAttribute("user");
        if (user != null)
            System.out.println("session destroyed for " + user.getFirstName());
        else
            System.out.println("session destroyed for anonymous");

    }

}
