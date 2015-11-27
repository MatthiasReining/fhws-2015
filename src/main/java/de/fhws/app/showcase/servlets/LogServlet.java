/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.servlets;

import de.fhws.app.business.logmanager.boundary.LogService;
import de.fhws.app.business.logmanager.entity.LogInfo;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author Matthias Reining
 */
@WebServlet("log")
public class LogServlet extends HttpServlet {

    @EJB
    LogService logService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            
            String message = req.getParameter("message");
            
            System.out.println("im log servlet (vor log aufruf)");
            logService.log(message);
            
            //FIXME bug
            Future<List<LogInfo>> asynResult = logService.findAllAsync();
            
            System.out.println("im log servlet (nach log aufruf)");
            resp.getWriter().println("log is done");
            
            try {
                System.out.println("heavy work");
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(LogServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("heavy work finished");
            
            
            List<LogInfo> list = asynResult.get();
            System.out.println("async information received");
            for (LogInfo li : list) {
                resp.getWriter().println(li.getId() + " - " + li.getMessage() + " " + li.getTs());
            }
            
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(LogServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
