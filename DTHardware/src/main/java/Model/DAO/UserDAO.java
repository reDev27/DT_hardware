package Model.DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

public class UserDAO extends BaseDAO
{
	public void register(String username, String eMail, String password, String nome, String cognome, String nTelefono, Calendar dataAcquisto, String nCarta, Calendar scadenza, int cvv, String userType, String passData) throws SQLException, NoSuchAlgorithmException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call registerUser(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("emailIn", eMail);
		callableStatement.setString("passwIn", password);
		callableStatement.setString("nomeIn", nome);
		callableStatement.setString("cognomeIn", cognome);
		callableStatement.setString("ntelefonoIn", nTelefono);
		callableStatement.setString("dataacquistoIn", DateUtil.PrepTime(dataAcquisto));
		callableStatement.setString("ncartaIn", nCarta);
		callableStatement.setString("scadenzaIn", DateUtil.PrepTime(scadenza));
		callableStatement.setString("cvvIn", String.valueOf(cvv));
		doExecute(callableStatement, userType, passData);
	}

	public boolean login(String username, String pass, String userType, String passData) throws SQLException, NoSuchAlgorithmException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call loginUser(?, ?, ?)}");
		callableStatement.setString("usernameIn", "admin00");
		callableStatement.setString("passwIn", pass);
		//callableStatement.registerOutParameter(3, Types.BOOLEAN);
		callableStatement.registerOutParameter("esito", Types.BOOLEAN);
		return (boolean) doExecute(callableStatement, "user", "Tav0l1n0").getBoolean("esito");
	}

}
