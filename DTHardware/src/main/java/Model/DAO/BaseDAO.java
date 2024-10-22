package Model.DAO;

import javax.security.auth.Destroyable;
import java.sql.*;
import java.util.TimeZone;

public abstract class BaseDAO implements Destroyable
{
	@Override
	public void destroy()
	{
		try
		{
			user.close();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		if(user!=null)
		{
			user=null;
		}
	}

	@Override
	public boolean isDestroyed()
	{
		if(user==null)
			return true;
		else
			return false;
	}

	public int doUpdate(String query, String userType, String pass) throws SQLException
	{
		openConnection(userType, pass);
		Statement statement = user.createStatement();
		int n = statement.executeUpdate(query);
		user.close();
		return n;
	}

	public ResultSet doStatement(String query, String userType, String pass) throws SQLException
	{
		openConnection(userType, pass);
		Statement statement= user.createStatement();
		result=statement.executeQuery(query);
		user.close();
		return result;
	}

	public CallableStatement doExecute(CallableStatement callableStatement) throws SQLException
	{
		callableStatement.execute();
		result=callableStatement.getResultSet();
		return callableStatement;
	}

	protected void openConnection(String userType, String pass)
	{
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e)
		{
			System.err.println("Errore nel caricamento del driver JDBC\n" + e.getMessage());
		}
		if(user==null)
		{
			try
			{
				user = DriverManager.getConnection(URL, userType, pass);
			}
			catch (SQLException throwable)
			{
				throwable.printStackTrace();
			}
		}
	}

	protected Connection user;
	private ResultSet result;
	protected final String URL="jdbc:mysql://localhost:3306/dthw?serverTimezone=" + TimeZone.getDefault().getID();

protected ResultSet getResult()
{
	return result;
}
protected void setResult(ResultSet result)
{
	this.result = result;
}
}