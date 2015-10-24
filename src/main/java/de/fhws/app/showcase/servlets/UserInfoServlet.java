package de.fhws.app.showcase.servlets;

import com.sun.net.httpserver.HttpServer;
import de.fhws.app.business.usermanagement.entity.AppUser;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 @WebServlet("user-info")
public class UserInfoServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        PrintWriter writer = resp.getWriter();
        
        AppUser user = (AppUser) session.getAttribute("user");
        if (user == null) {
            writer.println("Kein User angemeldet");
            return;
        }
        
        writer.println("User " + user.getFirstName() + " is angemeldet");
        
        
    }

     
}
