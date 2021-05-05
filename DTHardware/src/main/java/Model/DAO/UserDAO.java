package Model;

import java.sql.*;

public class UserDAO
{

	public int doUpdate(String query) throws SQLException
	{
		openConnection();
		Statement statement= user.createStatement();
		return statement.executeUpdate(query);
	}

	public ResultSet doStatement(String query) throws SQLException
	{
		openConnection();
		Statement statement= user.createStatement();
		result=statement.executeQuery(query);
		return result;
	}

	public void openConnection()
	{
		if(user==null)
		{
			try
			{
				user = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/clienti?serverTimezone=UTC", "root", "aaaa");
			}
			catch (SQLException throwable)
			{
				throwable.printStackTrace();
			}
		}
	}

	private Connection user;
	private ResultSet result;

public ResultSet getResult()
{
	return result;
}
public void setResult(ResultSet result)
{
	this.result = result;
}
public Connection getUser()
{
	return user;
}
public void setUser(Connection user)
{
	this.user = user;
}
}