package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dkte.pizzashop.entities.Customer;
import com.dkte.pizzashop.utils.DBUtils;
import com.mysql.cj.protocol.Resultset;

public class CustomerDao implements AutoCloseable
{

	private Connection connection ;
	public CustomerDao() throws SQLException
	{
		connection=DBUtils.getConnection();
	}
	@Override
	public void close() throws Exception 
	{
		if(connection!=null)
		{
			connection.close();
		}	
	}
	
	public void insertCustomer(Customer customer)throws SQLException
	{
		String sql="INSERT INTO CUSTOMER(name,email,password,mobile) values(?,?,?,?)";
		try(PreparedStatement insertStatement=connection.prepareCall(sql))
		{
			insertStatement.setString(1,customer.getName());
			insertStatement.setString(2,customer.getEmail());
			insertStatement.setString(3,customer.getPassword());
			insertStatement.setString(4,customer.getMobile());
			
			insertStatement.executeUpdate();
		}
				
	}
	
	public Customer getCustomer(String email,String password) throws SQLException 
	{
		String sql="select * from customer where email=? and password=? ";
		
		
		try(PreparedStatement selectStatement=connection.prepareCall(sql))
		{
			selectStatement.setString(1, email);
			selectStatement.setString(2, password);
			
			ResultSet rs=selectStatement.executeQuery();
			if(rs.next())
			{
				Customer customer =new Customer();
				customer.setCid(rs.getInt(1));
				customer.setName(rs.getString(2));
				customer.setEmail(email);
				customer.setPassword(password);
				customer.setMobile(rs.getString(5));
				
				return customer;
			}
			return null;
		}
	}

}
