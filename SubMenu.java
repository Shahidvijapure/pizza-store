package com.dkte.pizzashop.main;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.dkte.pizzashop.dao.OrderDao;
import com.dkte.pizzashop.dao.PizzaDao;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class SubMenu 
{

	public static int menu(Scanner sc)
	{
		System.out.println("****WELCOME TO PIZZA STORE ****");
		System.out.println("0.LOGOUT");
		System.out.println("1.Pizza Menu ");
		System.out.println("2.Order a Pizza");
		System.out.println("3.Order History");
		System.out.println("Enter your choice :");
		int choice =sc.nextInt();
		
		System.out.println("*******************************");
		return choice;
		
	}
	
	public static void displayMenu()
	{
		try(PizzaDao pizzaDao=new PizzaDao())
		{
			List<Pizza> pizzaList=pizzaDao.getAllPizza();
			pizzaList.forEach(p->System.out.println(p));
			
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
	
	public static void orderPizza(Scanner sc,int cid) 
	{
		try(OrderDao orderDao=new OrderDao())
		{
			System.out.println("Enter the pizza id you want to order ");
			int mid=sc.nextInt();
			orderDao.PlaceOrder(cid, mid);
			System.out.println("Order Placed.......:)");
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	public static void getOrders(int cid) 
	{
		try(OrderDao orderDao=new OrderDao())
		{
			List<Pizza> orderList=orderDao.getAllOrders(cid);
            orderList.forEach(e->System.out.println(e));
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public static void subMenu(Scanner sc,Customer customer)
	{
	     int  choice;
		  System.out.println("login successful");
		  while((choice = menu(sc))!=0)
		  {
			  switch(choice)
			  {
			  	case 1:
			  			System.out.println("***pizza menu*** ");
			  			displayMenu();
			  			break;
			  	case 2:
			  		    //System.out.println("customer id="+customer.getCid());
			  		    orderPizza(sc,customer.getCid());
			  			break;
			  	case 3:
		  		        getOrders(customer.getCid());
		  			    break;
			  			
			  	default:System.out.println("Wrong Choice.......:(");
			  			break;
			  	   	
			  }
		  }
	}
}
