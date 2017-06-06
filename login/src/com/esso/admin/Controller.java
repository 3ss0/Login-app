package com.esso.admin;

import java.io.File;
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
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out=response.getWriter();
		// collecting inputs for registeration 
		String userName=request.getParameter("username");
		String password=request.getParameter("password");
		String birthDate=request.getParameter("birthdate");
		String gender=request.getParameter("gender");
		try {
			// validating inputs 
			String result=Tools.processRegister(userName,password,birthDate,gender,true);
			// if inputs passed the validation 	: redirect user to login page !
			if (result=="")
				{
					response.sendRedirect("login.jsp");
					
				}
			// if it didn't pass : show user the error message
			else 
				{
					RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
					request.setAttribute("error", result);
					rd.forward(request, response);
					
				}
				
			
		} catch (Exception e) {
			out.println("Error : "+e.getMessage());
		}
		
	}
	

}
