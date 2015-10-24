package de.fhws.app.showcase.servlets;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("time")
public class TimeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Date time = new Date();
        req.setAttribute("serverTime", time);

        req.getRequestDispatcher("WEB-INF/time.jsp").forward(req, resp);

    }

}
