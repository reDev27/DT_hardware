package Model.DAO;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class UserNotLoggedDAO extends BaseDAO
{
	protected void updateUserCartaDiCredito(String username, String nCarta, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call updateUserCartaDiCredito(?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("ncartaIn", nCarta);
		doExecute(callableStatement);
	}

 	protected void insertCartaCredito(String nCarta, Calendar scadenza, int cvv,String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call insertCartaDiCredito(?, ?, ?)}");
		callableStatement.setString("ncartaIn", nCarta);
		callableStatement.setString("scadenzaIn", DateUtil.getStringFromCalendar(scadenza));
		callableStatement.setString("cvvIn", String.valueOf(cvv));
		doExecute(callableStatement);

	}

	protected void register(String username, String eMail, String password, String nome, String cognome, String nTelefono, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call registerUser(?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("emailIn", eMail);
		callableStatement.setString("passwIn", password);
		callableStatement.setString("nomeIn", nome);
		callableStatement.setString("cognomeIn", cognome);
		callableStatement.setString("ntelefonoIn", nTelefono);
		doExecute(callableStatement);
	}

	protected boolean login(String username, String pass, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call loginUser(?, ?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("passwIn", pass);
		callableStatement.registerOutParameter("esito", Types.BOOLEAN);
		return doExecute(callableStatement).getBoolean("esito");
	}

	protected void insertIndirizzo(String via,int ncivico,String citta, int cap, boolean flag, String username, String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertIndirizzo(?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("viaIn", via);
		callableStatement.setString("ncivicoIn", String.valueOf(ncivico));
		callableStatement.setString("cittaIn", citta);
		callableStatement.setString("capIn", String.valueOf(cap));
		callableStatement.setString("flagIn", String.valueOf(flag));
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	protected ResultSet selectProdottoByCodiceABarre(String codiceABarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call selectProdottoByCodiceABarre(?)}");
		callableStatement.setString("codiceABarreIn", codiceABarre);
		doExecute(callableStatement);
		return getResult();
	}

	protected ResultSet selectProdottoByCategoria(String categoria, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call selectProdottoByCategoria(?)}");
		callableStatement.setString("categoriaIn", categoria);
		doExecute(callableStatement);
		return getResult();
	}

	protected ResultSet selectMostRecentProducts(String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectMostRecentProducts()}");
		doExecute(callableStatement);
		return getResult();
	}

	protected ResultSet selectCategoria(String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call selectCategoria()}");
		doExecute(callableStatement);
		return getResult();
	}
}
