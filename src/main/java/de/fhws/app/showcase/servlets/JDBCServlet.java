package de.fhws.app.showcase.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("jdbc")
public class JDBCServlet extends HttpServlet {
    
    @Resource(lookup = "java:jboss/datasources/FHWS-DS")
    DataSource ds;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            //DataSource ds = (DataSource) InitialContext.doLookup("java:jboss/datasources/FHWS-DS");
            Connection connection = ds.getConnection();
            
            
            resp.getWriter().println("JDBC Servlet\n\n");
            
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM TEST order by id");
            while (rs.next()) {
                long id = rs.getLong(1);
                String name = rs.getString(2);
                resp.getWriter().println(id + " - " + name);
            }

            

        } catch (SQLException ex) {
            Logger.getLogger(JDBCServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
