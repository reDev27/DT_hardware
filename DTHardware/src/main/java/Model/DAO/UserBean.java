package Model.DAO;

import java.sql.SQLException;

public class UserBean
{
	public void callInsertCategoria(String nome, int quantita, int codiceaBarre) throws SQLException  ////////////
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertCategoria(nome, quantita, codiceaBarre, "root", "aaaa");
		connection.destroy();
	}

	public void callInsertCompone(int nprodotti, int id, String codiceABarre) throws SQLException /////////
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertCompone(nprodotti, id, codiceABarre, "root", "aaaa");
		connection.destroy();
	}
}
