package de.fhws.app.showcase.servlets;

import de.fhws.app.showcase.ejb.FirstService;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("transaction-test")
public class TransactionTestServlet extends HttpServlet {

    @EJB
    FirstService fs;
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fs.s1();
        
        resp.getWriter().println("ok");
    }
    
    
    
}
