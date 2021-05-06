package Model.DAO;

import javax.security.auth.Destroyable;
import java.sql.*;

public class UserDAO implements Destroyable
{
	@Override
	public void destroy()
	{
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
		//CallableStatement callableStatement=user.prepareCall("call loginUser(\""+nickname+"\", \""+passwordInseritaHashed+"\", @esito);");
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

	public Object doExecute(String query, String userType, String pass) throws SQLException, NoSuchAlgorithmException
	{
		openConnection(userType, pass);
		CallableStatement callableStatement=user.prepareCall(query);
		callableStatement.setString(1, "admin00");
		callableStatement.setString(2, UserBean.preparaPassword("adin"));			//DA MODIFICARE
		//callableStatement.registerOutParameter(3, Types.BOOLEAN);
		callableStatement.registerOutParameter("esito", Types.BOOLEAN);
		callableStatement.execute();
		boolean b=callableStatement.getBoolean("esito");
		user.close();
		return b;
	}

	private void openConnection(String userType, String pass)
	{
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

	private Connection user;
	private ResultSet result;
	private final String URL="jdbc:mysql://127.0.0.1:3306/dthw?serverTimezone=UTC";

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