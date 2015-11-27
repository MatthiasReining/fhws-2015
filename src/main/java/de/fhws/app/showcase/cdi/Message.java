/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.cdi;

import java.util.Date;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 *
 * @author Matthias Reining
 */
public class Message {

    private String message = "Hello World!";

    @Produces
    public String factory2() {
        return "String message";
    }

    @Produces
    @Killer
    public String factory3() {
        return "Killer Message!!!";
    }

    @Produces
    @Special
    public Message factory1() {
        Message m = new Message();
        m.message = "Hello World " + new Date();
        return m;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
