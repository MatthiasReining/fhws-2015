/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.cdi;

import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

/**
 *
 * @author Matthias Reining
 */
public class Message {
    
    @Inject
    Message2 m2;

    private String message = "Hello World" + new Date();

    public String getMessage() {
        String m2Text = m2.getMessage2();
        System.out.println(m2Text);
        return message + m2Text;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
