/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.cdi;

import javax.enterprise.inject.Produces;

/**
 *
 * @author Matthias Reining
 */
public class Factory {

    @Produces    
    public Message2 factoryM2() {
        return new Message2("Second World");
    }

}
