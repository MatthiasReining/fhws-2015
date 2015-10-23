package de.fhws.app.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = {"/*"})
public class BlockFFFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                
        String userAgent = ((HttpServletRequest)request).getHeader("User-Agent");
        
        if (userAgent.contains("Firefox")) {
            response.getWriter().println("Firefox mögen wir nicht!");
            return;
        }
        
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
    }

}
