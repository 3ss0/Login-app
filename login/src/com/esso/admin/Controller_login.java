package com.esso.admin;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Controller_login
 */
@WebServlet("/Controller_login")
public class Controller_login extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String _username=request.getParameter("username");
		String _password=request.getParameter("password");
		
		// validating username and password from the xml file 
		boolean result=Store.validateLogin(_username, _password);
		
		// if it matches : create a session and redirect user to welcome page(home page)
		if (result){
			HttpSession session = request.getSession();
            session.setAttribute("user", _username);
            response.sendRedirect("welcome.jsp");
        
            
		}
		// if it didn't match , give the user the error message 
		else {
			RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
			request.setAttribute("error", " Wrong username or password !");
			rd.forward(request, response);
		}
	}

}
