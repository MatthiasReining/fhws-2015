/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.business.logmanager.controller;

import de.fhws.app.business.logmanager.boundary.LogEvent;
import javax.enterprise.event.Observes;

/**
 *
 * @author Matthias Reining
 */
public class LogEventManager {

    public void manageLogEventsAtConsole(@Observes @LogEvent String message) {
        System.out.println("logevent: " + message);

    }
}
