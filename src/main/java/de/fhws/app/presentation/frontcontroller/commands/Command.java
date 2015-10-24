/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.presentation.frontcontroller.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matthias Reining
 */
public interface Command {
    
    public String execute(HttpServletRequest req, HttpServletResponse resp);
    
}
