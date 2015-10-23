package de.fhws.app.presentation;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("server-side-include")
public class ServerSideIncludeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        PrintWriter writer = resp.getWriter();
        
        writer.println("<h1>Hallo FHWS</h1>");
        
        RequestDispatcher rd = req.getRequestDispatcher("/FirstServlet");
        rd.include(req, resp);
        
        writer.println("<h2>Das Ende</h2>");

    }

}
