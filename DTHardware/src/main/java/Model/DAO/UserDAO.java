package Model.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;

public class UserDAO extends UserNotLoggedDAO
{

	public void insertCompone(int nprodotti, int id, String codiceABarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCompone(?)}");
		callableStatement.setString("nprodottiIn", String.valueOf(nprodotti));
		callableStatement.setString("idIn", String.valueOf(id));
		callableStatement.setString("codiceABarreIn", codiceABarre);
		doExecute(callableStatement);

	}

	public void insertOrdine(int id, int sconto, double totale,String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertOrdine(?, ?, ?)}");
		callableStatement.setString("idIn", String.valueOf(id));
		callableStatement.setString("quantitaIn", String.valueOf(sconto));
		callableStatement.setString("codiceabarreIn", String.valueOf(totale));
		doExecute(callableStatement);
	}

}
