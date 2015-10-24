package de.fhws.app.showcase.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"lifecycle"}, loadOnStartup = 1)
public class LifecycleServlet extends HttpServlet {
    
    int counter = 0;

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("lifecycle init");
        super.init(config);
    }

    @Override
    public void destroy() {
        System.out.println("lifecycle destroy");
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("in doGet | counter: " + counter++);
        resp.getWriter().println("Lifecycle Servlet");
    }
    
    
}
