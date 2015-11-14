/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.servlets;

import de.fhws.app.business.usermanagement.entity.AppUser;
import de.fhws.app.business.usermanagement.entity.Statistics;
import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("statistics")
public class StatisticsServlet  extends HttpServlet{

    @PersistenceContext
    EntityManager em;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        //example for bidirectional
        Statistics s = em.find(Statistics.class, 1l);
        
        AppUser user = s.getAppUser();
        resp.getWriter().println(user.getFirstName());
    }
    
}
