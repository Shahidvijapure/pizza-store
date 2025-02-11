package com.dkte.pizzashop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dkte.pizzashop.entities.Pizza;
import com.dkte.pizzashop.utils.DBUtils;

public class OrderDao implements AutoCloseable {
	
	Connection connection;
	
	public OrderDao() throws SQLException {
		connection=DBUtils.getConnection();
	}
	
	public void PlaceOrder(int cid,int mid) throws SQLException
	{
		String sql="insert into orders(cid,mid) values(?,?)";
		try(PreparedStatement insertstatement = connection.prepareCall(sql))
		{
			insertstatement.setInt(1,cid );
			insertstatement.setInt(2,mid );
			insertstatement.executeUpdate();
		}
	}
	
	public List<Pizza> getAllOrders(int cid) throws SQLException
	{
		List<Pizza> orderList=new ArrayList<Pizza>();
		String sql="select m.* from menu m inner join orders o on o.mid=m.mid where o.cid=?";
		try(PreparedStatement selectstat=connection.prepareCall(sql))
		{
			selectstat.setInt(1, cid);
			ResultSet rs=selectstat.executeQuery();
			while(rs.next())
			{
				Pizza pizza =new Pizza();
				pizza.setMid(rs.getInt(1));
				pizza.setName(rs.getString(2));
				pizza.setDescription(rs.getString(3));
				pizza.setPrice(rs.getDouble(4));
				
				orderList.add(pizza);
				
			}
			return orderList;
		}
		
		
	}

	@Override
	public void close() throws Exception {
		if(connection!=null)
		{
			connection.close();
		}
		
	}

}
