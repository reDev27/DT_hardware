package Model.DAO;

import java.sql.SQLException;

public class UserBean extends UserNotLoggedBean
{

	public static void callInsertCompone(int nprodotti, int id, String codiceABarre) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertCompone(nprodotti, id, codiceABarre, "root", "aaaa");
		connection.destroy();
	}

	public static void callInsertOrdine(int id, int sconto, double totale) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertOrdine(id, sconto, totale, "root", "aaaa");
		connection.destroy();
	}
}
