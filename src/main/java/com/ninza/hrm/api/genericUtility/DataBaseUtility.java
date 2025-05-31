package com.ninza.hrm.api.genericUtility;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DataBaseUtility 
{
	FileUtility fLib=new FileUtility();
	
	public Connection con;
	public Statement stat;
	public void connectDataBase() throws SQLException, IOException
	{
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(fLib.getDataFromProperties("DB_Url"), fLib.getDataFromProperties("DB_Username"), fLib.getDataFromProperties("DB_Password"));	
		stat = con.createStatement();
	}
	public ResultSet executeQuery(String query) throws SQLException
	{
		ResultSet resultSet = stat.executeQuery(query);
		return resultSet;
	}
	
	public int executeUpdate(String query) throws SQLException
	{
		int update = stat.executeUpdate(query);
		return update;
	}
	
	public String verifyData(String query, int colIndex, String expectedData) throws SQLException
	{
		boolean flag=false;
		ResultSet result = con.createStatement().executeQuery(query);
		
		while(result.next())
		{
			if(result.getString(colIndex).equals(expectedData))
			{
				flag=true;
				break;
			}
		}
		if(flag)
		{
			System.out.println(expectedData+" data is verified in database table");
			return expectedData;
		}
		else
		{
			System.out.println(expectedData+" is not verified in database table");
			return expectedData;
		}
	}
	
	public void closeDBConnection() throws SQLException
	{
		con.close();
	}
}
