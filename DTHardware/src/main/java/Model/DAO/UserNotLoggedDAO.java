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

	protected Map<String, Object> selectProdotto(String codiceABarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call selectProdotto(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("codiceABarreIn", codiceABarre);
		callableStatement.registerOutParameter("codiceABarreOut", Types.CHAR);
		callableStatement.registerOutParameter("prezzoOut", Types.DOUBLE);
		callableStatement.registerOutParameter("descrizioneOut", Types.VARCHAR);
		callableStatement.registerOutParameter("specificheOut", Types.VARCHAR);
		callableStatement.registerOutParameter("immagineOut", Types.BLOB);
		callableStatement.registerOutParameter("quantitaOut", Types.INTEGER);
		callableStatement.registerOutParameter("marcaOut", Types.VARCHAR);
		callableStatement.registerOutParameter("modelloOut", Types.VARCHAR);
		callableStatement=doExecute(callableStatement);
		Map<String, Object> risultati= new HashMap<String, Object>();
		risultati.put("codiceABarreOut", callableStatement.getString("codiceABarreOut"));
		risultati.put("prezzoOut",callableStatement.getDouble("prezzoOut"));
		risultati.put("descrizioneOut", callableStatement.getString("descrizioneOut"));
		risultati.put("specificheOut", callableStatement.getString("specificheOut"));
		risultati.put("immagineOut", callableStatement.getBlob("immagineOut"));
		risultati.put("quantitaOut", callableStatement.getInt("quantitaOut"));
		risultati.put("marcaOut", callableStatement.getString("marcaOut"));
		risultati.put("modelloOut", callableStatement.getString("modelloOut"));
		return risultati;
	}

	protected ResultSet selectProdottoByCategoria(String categoria, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call selectCategoria(?)}");
		callableStatement.setString("categoriaIn", categoria);
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
