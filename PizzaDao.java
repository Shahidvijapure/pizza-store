package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.utils.DBUtils;
import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.entities.Pizza;

public class PizzaDao implements AutoCloseable 
{
    Connection connection;
    public PizzaDao() throws SQLException 
    {
	connection=DBUtils.getConnection();
    }
    
	@Override
	public void close() throws Exception {
		
		if(connection!=null)
		{
			connection.close();
		}	
	}
	
	public List<Pizza> getAllPizza() throws SQLException
	{
		String sql="select * from menu";
		List<Pizza> pizzaList=new ArrayList<Pizza>();
		try(PreparedStatement selectStatement=connection.prepareCall(sql))
		{
			ResultSet rs=selectStatement.executeQuery();
			while(rs.next())
			{
				Pizza pizza=new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				
				pizzaList.add(pizza);
			}
			return pizzaList;
		}
	}
}
