package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.Scanner;

import com.dkte.pizzashop.dao.CustomerDao;
import com.dkte.pizzashop.entities.Customer;

public class Mainmenu {
	
	public static Customer loginCustomer(Scanner sc) 
	{
		try(CustomerDao customerDao = new CustomerDao())
		{
			System.out.println("enter the email :");
			String email=sc.next();
			System.out.println("enter the password :");
			String password=sc.next();
			
			return customerDao.getCustomer(email, password);
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static void registerCustomer(Scanner sc)
	{
		Customer customer=new Customer();
		customer.accept(sc);
		
		try(CustomerDao customerDao=new CustomerDao())
		{
			customerDao.insertCustomer(customer);
			System.out.println("customer registered.....2");
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		
	}
	
	public static int menu(Scanner sc)
	{
		System.out.println("****WELCOME TO PIZZA STORE ****");
		System.out.println("0.EXIT");
		System.out.println("1.LOGIN");
		System.out.println("2.REGISTER");
		System.out.println("Enter your choice :");
		int choice =sc.nextInt();
		
		System.out.println("*******************************");
		return choice;
		
	}

	public static void main(String[] args) 
	{
	  Scanner sc=new Scanner(System.in);
      int  choice;
	  
	  while((choice = menu(sc))!=0)
	  {
		  switch(choice)
		  {
		  	case 1:
		  			Customer customer=loginCustomer(sc);
		  			if(customer !=null)
		  				SubMenu.subMenu(sc,customer);
		  			else
		  				System.out.println("INVALID CREDENTIALS....");
		  			break;
		  	case 2:
		  		    registerCustomer(sc);
		  			break;
		  			
		  	default:System.out.println("Wrong Choice.......:(");
		  			break;
		  	   	
		  }
	  }
	 

	}

}
