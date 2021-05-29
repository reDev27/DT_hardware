package Model.DAO;

import java.sql.SQLException;
import java.util.Calendar;

public class UserBean extends UserNotLoggedBean
{

	public static void callInsertCompone(int nprodotti, int id, String codiceABarre) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertCompone(nprodotti, id, codiceABarre, "root", "aaaa");
		connection.destroy();
	}

	public static  void callInsertOrdine(int id, int sconto, double totale, Calendar dataacquisto, String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertOrdine(id, sconto, totale,dataacquisto,username, "root", "aaaa");
		connection.destroy();
	}
}
