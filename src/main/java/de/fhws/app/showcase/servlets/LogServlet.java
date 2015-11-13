/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.servlets;

import de.fhws.app.business.logmanager.boundary.LogService;
import de.fhws.app.business.logmanager.entity.LogInfo;
import java.io.IOException;
import javax.annotation.Resource;
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

    @Resource(lookup = "java:jboss/datasources/FHWS-DS")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");

        LogService ls = new LogService(ds);
        ls.log(message);
        resp.getWriter().println("log is done");
        for (LogInfo li : ls.findAll()) {
            resp.getWriter().println(li.getId() + " - " + li.getMessage() + " " + li.getTs());
        }

    }

}
