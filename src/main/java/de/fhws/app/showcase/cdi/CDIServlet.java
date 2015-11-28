/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.cdi;

import java.io.IOException;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Matthias Reining
 */
@WebServlet("cdi")
public class CDIServlet extends HttpServlet {

    @Inject
    @Special
    Message message;

    @Inject
    String stringMessage;

    @Inject
    @Killer
    String killerMessage;

    @Inject
    Message2 message2;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String m = message.getMessage();
        resp.getWriter().println("message2: " + message2.getMessage2());
        resp.getWriter().println(m);
        resp.getWriter().println("\n");
        resp.getWriter().println(stringMessage);
        resp.getWriter().println("\n");
        resp.getWriter().println(killerMessage);

    }

}
