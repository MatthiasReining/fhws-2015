/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.fhws.app.showcase.servlets;

import de.fhws.app.showcase.ejb.CalcService;
import de.fhws.app.showcase.ejb.CalculationBeanRemote;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("ejb")
public class EJBTestServlet extends HttpServlet {

    @EJB
    CalcService calcService;
    
    @EJB
    CalculationBeanRemote cbr;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.getWriter().println( calcService.calculation() );
        
        resp.getWriter().println( cbr.calculation(5));
    }
    
    

}
