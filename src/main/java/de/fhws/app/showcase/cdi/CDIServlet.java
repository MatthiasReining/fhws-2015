/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.cdi;

import java.io.IOException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Instance;
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
    Instance<Message> messageProducer;

    public Message getMessageObj() {
        return messageProducer.get();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String m = getMessageObj().getMessage();
        resp.getWriter().println(m);
    }

}
