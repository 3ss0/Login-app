package com.esso.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class errorPagefilter
 */
@WebFilter("/errorPagefilter")
public class errorPageFilter implements Filter {

    /**
     * Default constructor. 
     */
    public errorPageFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	
	// filter for Bulk error page
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        
        // if there's a session : redirect user to welcome page (home page)
        if (session != null || session.getAttribute("user") != null) {
            res.sendRedirect(req.getContextPath() + "/welcome.jsp"); //  logged-in user found, so redirect to welcome page.
        } 
        // if there's no session : redirect user to login page
        else {
        	res.sendRedirect(req.getContextPath() + "/login.jsp"); // not Logged-in user found, so redirect to login page.
        }
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
