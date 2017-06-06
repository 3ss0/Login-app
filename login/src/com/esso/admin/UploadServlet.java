package com.esso.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*10,      // 10MB
maxRequestSize=1024*1024*50)   // 50MB
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static int count=0; // for counting registered users from csv file
       
  // class for uploading csv files and pasring them 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> list=new ArrayList<>();
		List<String> errorList=new ArrayList<>();
		String error="";
		String res="";
		// get users info part
        InputStream input=request.getPart("file").getInputStream();
        // check content type
        String type=request.getPart("file").getContentType();
        
            System.out.println(type);
            Scanner scanner = new Scanner(input);
            // scan the csv file line by line 
            while (scanner.hasNextLine()) {
                list.add(scanner.nextLine()); // adding each line to list
            }
            // looping through the list of users info 
            for (String temp:list)
            {
            	System.out.println("line length : "+temp.length());
            	if(temp.trim().isEmpty()) // checking if a line is empty 
            	{
            		errorList.add("empty line ");
            		continue; // escape empty line processing and adding empty line error to error list
            	}
            	// processing line info 
            	String[] params=temp.split(",");
            	
            	if(params.length<4 || params.length>4)
            	{
            		errorList.add(" wrong number of inputs on this line ");
            		continue; // escape wrong number of inputs processing and adding error to error list
            		
            	}
            	res=Tools.processRegister(params[0],params[1],params[2],params[3],false);
            	if (res.length()>0)
            	{
            		// adding the error to error list if the line info didn't pass the validation
            		error=params[0]+" has the following errors : "+res;
    				errorList.add(error);
            	}else 
            	{
            		// adding 1 to regieterd users 
            		count++;
            	}
            	
            }
            
            // checking if error list is empty 
            if(errorList.isEmpty())
            {
            	// if error list is empty : means that all users info in file were registered successfully !
            	String message="Done successfully ! total number of registered users  "+count;
        		RequestDispatcher rd = request.getRequestDispatcher("BulkRegisteration.jsp");
        		request.setAttribute("error",  message);
        		count=0; // resetting count for next file upload 
        		rd.forward(request, response);
                
            }else
            {
            	// if error list contains error
            	errorList.add("total number of registered users  "+count);
            	// redirecting user to error page ! 
            	RequestDispatcher rd = request.getRequestDispatcher("BulkErrorPage.jsp");
        		request.setAttribute("error",  errorList);
        		count=0;
        		rd.forward(request, response);
            	
            }
            scanner.close();
            
            
         
        
	}
        

}
