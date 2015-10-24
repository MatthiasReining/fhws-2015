/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.frontcontroller.commands;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matthias Reining
 */
public class TimeCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        
        Date serverTime = new Date();
        
        req.setAttribute("serverTime", serverTime );
        return "date-time.jsp";
    }

}
