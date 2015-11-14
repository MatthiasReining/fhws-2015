/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.servlets;

import de.fhws.app.business.logmanager.boundary.LogService;
import de.fhws.app.business.logmanager.entity.LogInfo;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

@WebServlet("log2")
public class JPAServlet extends HttpServlet {

    @PersistenceContext
    EntityManager em;

    @Resource
    UserTransaction ut;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String message = req.getParameter("message");
        
        if (req.getParameter("select").equals("true")) {
            
            LogInfo li = em.find(LogInfo.class, 2l);
            
            resp.getWriter().println(li.getId() + " - " + li.getMessage() + " - " +li.getTs());
            return;
        }

        LogInfo li = new LogInfo();
        li.setMessage(message);
        li.setTs(new Date());
        try {
            ut.begin();
            
            em.persist(li);

            ut.commit();
        } catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
            Logger.getLogger(JPAServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
