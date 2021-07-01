package Model.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class UserDAO extends UserNotLoggedDAO
{
	public ResultSet selectOrdersByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectOrdiniByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
		return getResult();
	}

	public ResultSet selectCarteDiCreditoByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectCartadicreditoByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
		return getResult();
	}

	public ResultSet selectIndirizzoByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectIndirizzoByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
		return getResult();
	}

	public ResultSet selectClienteByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectClienteByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
		return getResult();
	}

	public void insertCompone(int nprodotti, String codiceABarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCompone(?, ?)}");
		callableStatement.setString("nprodottiIn", String.valueOf(nprodotti));
		callableStatement.setString("codiceABarreIn", codiceABarre);
		doExecute(callableStatement);
	}

	public void insertOrdine(String fattura, double totale, Calendar dataacquisto, String username,String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertOrdine(?, ?, ?, ?)}");
		callableStatement.setString("fatturaIn", fattura);
		callableStatement.setString("totaleIn", String.valueOf(totale));
		callableStatement.setString("dataacquistoIn", DateUtil.getStringFromCalendar(dataacquisto));
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

}
