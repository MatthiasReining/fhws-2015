package de.fhws.app.showcase.frontcontroller;

import de.fhws.app.showcase.frontcontroller.commands.Command;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("controller/*")
public class FrontControllerServlet extends HttpServlet {

    void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String pathInfo = req.getPathInfo();
        String clazzName = pathInfo.substring(1,2).toUpperCase() + pathInfo.substring(2) + "Command";
        
        String fqn = "de.fhws.app.presentation.frontcontroller.commands." + clazzName;
        System.out.println(fqn);
        
        try {
            Class cmdClazz = Class.forName(fqn);
            Object obj = cmdClazz.newInstance();
            Command cmd = (Command)obj;
            
            String nextPage = cmd.execute(req, resp);
            
            System.out.println("next Page: " + nextPage);
            //Alternativer Aufruf der Methode über Reflection.
            //Method m = cmdClazz.getMethod("execute", HttpServletRequest.class, HttpServletResponse.class);
            //m.invoke(obj, req, resp);
            
            req.getRequestDispatcher("/WEB-INF/" + nextPage).forward(req, resp);
            
            
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException ex) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    
}
