package com.esso.admin;

import java.io.File;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

// Store class for  storing  user inputs to xml file (our data storing  mechanism )
public class Store {
	private static Document doc;
	private static final File XML_FILE = new File("users.xml");
	private static final String[] ELEMS={"username","password","bdate","gender"};
	
	
	
	// storing user inputs to xml file 
	public static void store(String userName,String password,String birthDate,String gender)
	{
		

			String[] values={userName,password,birthDate,gender};
			if (Store.XML_FILE.exists())
			{
						 doc=getXmlFile(true);
				         // root element
				         Element root = doc.getDocumentElement();
				         Element user = doc.createElement("user");
				         root.appendChild(user);
				         // call create_elems method
				         doc=createElements(Store.ELEMS, values, doc, user);

				         // write the content into xml file
				         saveToXmlFile(doc);
				         
				
			}
				
			
			else {
				
				         doc = getXmlFile(false);
				         // root element
				         Element rootElement = doc.createElement("users");
				         doc.appendChild(rootElement);

				         //  user element
				         Element user = doc.createElement("user");
				         rootElement.appendChild(user);
				      // call create_elems method
				         doc=createElements(ELEMS, values, doc, user);

				         // write the content into xml file
				         saveToXmlFile(doc);
				         }
				
			
			
	}
	
	// get document to parse 
	public static Document getXmlFile(boolean state)
	{
		// state to detect if it's already exist or create new file 
		try {
		DocumentBuilderFactory dbFactory =
		         DocumentBuilderFactory.newInstance();
		         DocumentBuilder dBuilder = 
		            dbFactory.newDocumentBuilder();
		         if (state == true){
		        	  doc = dBuilder.parse(Store.XML_FILE);
		         }else {
		        	  doc = dBuilder.newDocument(); 
		         }
		          
		         
		    
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return doc;
		
	}
	
	// creating new user node !
	public static Document createElements(String[] elems,String[] values,Document doc,Element user){
		
	
	 for(int i=0;i<elems.length;i++)
	 {
		 Element elem=doc.createElement(elems[i]);
	     user.appendChild(elem);
	     elem.appendChild(doc.createTextNode(values[i]));
		 
	 }
	
	return doc;
	
	
	}
	
	// save document 
	public static void saveToXmlFile(Document doc)
	{
		try {
		 TransformerFactory transformerFactory =
		         TransformerFactory.newInstance();
		         Transformer transformer =
		         transformerFactory.newTransformer();
		         DOMSource source = new DOMSource(doc);
		         StreamResult result =
		         new StreamResult(Store.XML_FILE);
		         transformer.transform(source, result);
		         // Output to console for testing
		         StreamResult consoleResult =
		         new StreamResult(System.out);
		         transformer.transform(source, consoleResult);
		         }catch (Exception e)
		{
		        	 e.printStackTrace();
		}
		
	}
	
	// validate username and password for log in 
	public static Boolean validateLogin(String userName,String password)
	{	
		boolean result=false;
		doc=getXmlFile(Store.XML_FILE.exists());
		NodeList users = doc.getElementsByTagName("user");
		for(int i=0;i<users.getLength();i++)
		{
			Node node = users.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
               Element single_user = (Element) node;
                String realUserName=single_user.getElementsByTagName("username")
                  .item(0)
                  .getTextContent();
                String realPassword=single_user.getElementsByTagName("password")
                        .item(0)
                        .getTextContent();
                if (realUserName.equals(userName)&& realPassword.equals(Tools.encrypt(password)))
                {
                	result=true;
                	break;
                }
                }
		}
        return result; 
	}
	
	// check if user already exists 
	public static Boolean userExist(String userName) 
	{
		boolean result=false;
		doc=getXmlFile(Store.XML_FILE.exists());
		NodeList users = doc.getElementsByTagName("user");
		for(int i=0;i<users.getLength();i++)
		{
			Node node = users.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
               Element single_user = (Element) node;
                String realUserName=single_user.getElementsByTagName("username")
                  .item(0)
                  .getTextContent();
                
                if (realUserName.equals(userName))
                {
                	result=true;
                	break;
                }
                }
		}
        return result; 
	}
	
	}
