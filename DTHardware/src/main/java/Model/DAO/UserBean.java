package Model.DAO;

import Model.CreditCard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserBean extends UserNotLoggedBean
{
	public static void callSelectCarteDiCreditoByUsername(String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.selectCarteDiCreditoByUsername(username, "root", "aaaa");
		ResultSet result=connection.getResult();
		ArrayList<CreditCard> creditCards=new ArrayList<>();
		String time="porcodiobastardo";
		while(result.next())
		{
			time=result.getString("scadenza");
			//creditCards.add(new CreditCard(result.getString("ncarta"), result.getString()))
		}
		System.out.println(time);
		connection.destroy();
	}

	public static void callSelectIndirizzoByUsername(String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.selectIndirizzoByUsername(username, "root", "aaaa");
		ResultSet result=connection.getResult();
		while(result.next())
		{

		}
		connection.destroy();
	}

	public static void callSelectClienteByUsername(String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.selectClienteByUsername(username, "root", "aaaa");
		ResultSet result=connection.getResult();
		while(result.next())
		{

		}
		connection.destroy();
	}

	public static void callInsertCompone(int nprodotti, int id, String codiceABarre) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertCompone(nprodotti, id, codiceABarre, "root", "aaaa");
		connection.destroy();
	}

	public static  void callInsertOrdine(int id, int sconto, double totale, Calendar dataAcquisto, String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertOrdine(id, sconto, totale,dataAcquisto,username, "root", "aaaa");
		connection.destroy();
	}
}
