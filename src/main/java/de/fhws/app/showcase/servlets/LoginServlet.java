package de.fhws.app.showcase.servlets;

import de.fhws.app.business.usermanagement.entity.AppUser;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");
        
        email = (email == null) ? "" : email;
        password = (password == null) ? "" : password;
        
        if (!email.equals(password)) {
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        AppUser user = new AppUser();
        user.setEmail(email);
        user.setFirstName(email);
        user.setLastLogin(new Date());
        
        HttpSession session = req.getSession(true);
        session.setAttribute("user", user);
        
        resp.getWriter().println("Welcome " + user.getFirstName());
    }

}
