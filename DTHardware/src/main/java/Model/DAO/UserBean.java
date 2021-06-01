package Model.DAO;

import java.sql.SQLException;
import java.util.Calendar;

public class UserBean extends UserNotLoggedBean
{
	public static void callSelectCarteDiCreditoByUsername(String username)
	{
		UserDAO connection=new UserDAO();
		connection.selectCarteDiCreditoByUsername(username, "root", "aaaa");
		connection.destroy();
	}

	public static void callSelectIndirizzoByUsername(String username)
	{
		UserDAO connection=new UserDAO();
		connection.selectIndirizzoByUsername(username, "root", "aaaa");
		connection.destroy();
	}

	public static void callSelectClienteByUsername(String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.selectClienteByUsername(username, "root", "aaaa");
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
