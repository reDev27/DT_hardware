package Model.DAO;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class UserDAO extends UserNotLoggedDAO
{
	public void selectCarteDiCreditoByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectCartadicreditoByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public void selectIndirizzoByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectIndirizzoByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public void selectClienteByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectClienteByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public void insertCompone(int nprodotti, int id, String codiceABarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCompone(?)}");
		callableStatement.setString("nprodottiIn", String.valueOf(nprodotti));
		callableStatement.setString("idIn", String.valueOf(id));
		callableStatement.setString("codiceABarreIn", codiceABarre);
		doExecute(callableStatement);
	}

	public void insertOrdine(int id, int sconto, double totale, Calendar dataacquisto, String username,String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertOrdine(?, ?, ?, ?, ?)}");
		callableStatement.setString("idIn", String.valueOf(id));
		callableStatement.setString("scontoIn", String.valueOf(sconto));
		callableStatement.setString("totaleIn", String.valueOf(totale));
		callableStatement.setString("dataacquistoIn", DateUtil.getStringFromCalendar(dataacquisto));
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

}
