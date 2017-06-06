package com.esso.admin;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

// class for validating user inputs 
public class Tools {
	private static final String PASSWORD_PATTERN="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,20}";
	private static final String USERNAME_PATTERN="[a-zA-Z0-9._-]{3,15}";
	private static final String BIRTHDATE_PATTERN="[0-9]{4}-(1[0-2]|0[1-9])-(3[01]|[12][0-9]|0[1-9])";
	private static final String[] PARAMS={USERNAME_PATTERN,PASSWORD_PATTERN,BIRTHDATE_PATTERN};
	
	// encrypt passwords before saving them 
	public static String encrypt(String password)
	{
		StringBuffer sb = new StringBuffer();
		try {
		 MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(password.getBytes());

	        byte byteData[] = md.digest();

	        //convert the byte to hex format method 1
	        
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        
	        
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();

	}
	
	// validate user registration  inputs 
	public static String validateInput(String userName,String password,String birthDate,String gender)
	{
		String error="";
		if(userName.matches(USERNAME_PATTERN))
		{
			if (password.matches(PASSWORD_PATTERN))
			{
				if (birthDate.matches(BIRTHDATE_PATTERN))
				{
					boolean check=checkDate(birthDate);
					if (check)
					{
						
					}else
					{
						error=" User shoud be born between 2000 and 1975 ";
					}
					
				}else 
				{
					System.out.println(birthDate);
					error="date should be in format YYYY-MM-DD 	";
				}
				
			}else
			{
				error="password length shoud be between 8 and 20 characters has 1 Uppercase letter  and 1 number and doesn't contain any spaces";
			}
			
		}else 
		{
			error="username length shoud be between 3 and 15 characters and doesn't containe any spaces";
		}
		
		
		
		
		
		return error;
		
	}
	
	// check birth year of user ( should be between 2000 and 1975 )
	private static Boolean checkDate(String date)
	
	{
		boolean result=true;
		
		String[] parts=date.split("-");
//		for (int i = 0; i < parts.length; i++) {
//			System.out.println(parts[i]);
//		}
		if (date.matches(BIRTHDATE_PATTERN))
		{
			int year=Integer.parseInt(parts[0]);
			
			if (year<=2000 && year>=1975)
			{
			}else  
			{
				result=false;
			}
		}
		
		
		return result;
	}
	
	// regisetr new users 
	public static String processRegister(String userName,String password,String birthDate,String gender,boolean type)
    {
    	String res="";
    	String result;
    	if(type==true)
    	{
    		 result=Tools.validateInput(userName,password,birthDate,gender);
    	}else
    	{
    		result=Tools.validateBulkRegister(userName,password,birthDate,gender);
    	}
    	
    	if (!Store.userExist(userName))
		{
    		if(result.length()==0)
    		{
			String EncryptedPassword=Tools.encrypt(password);
			Store.store(userName,EncryptedPassword,birthDate,gender);
    		}else
    		{
    			res=result;
    		}
      
        
	}else {
		res="already registered user  ! ";
	}
    	return res;
    }
	
	
	// validating bulk register csv files 
	public static String validateBulkRegister(String userName,String password,String birthDate,String gender)
	{
		StringBuilder errors=new StringBuilder();
		if(!userName.matches(USERNAME_PATTERN))
		{
			errors.append("username length shoud be > 3 and doesn't containe any spaces");
			errors.append(",");
		}
		if(!password.matches(PASSWORD_PATTERN))
		{
			errors.append("password length shoud be between 8 and 20 characters has 1 Uppercase letter  and 1 number and doesn't contain any spaces");
			errors.append(",");
		}
		if (!birthDate.matches(BIRTHDATE_PATTERN))
		{
			errors.append("date should be in format YYYY-MM-DD 	");
			errors.append(",");
		}
		if(!checkDate(birthDate))
		{
			errors.append("User shoud be born between 2000 and 1975 ");
			errors.append(",");
		}
		if(gender.equalsIgnoreCase("Male") || gender.equalsIgnoreCase("Female"))
		{
		}else
		{
			errors.append("Gender should be Male or Female ");
		}
		
		return errors.toString();
	}
	
	
	
}
