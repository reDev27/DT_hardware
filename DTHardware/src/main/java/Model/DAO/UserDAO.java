package Model.DAO;

import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;

public class UserDAO extends BaseDAO
{
	public void updateUserCartaDiCredito(String username, String nCarta, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call updateUserCartaDiCredito(?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("ncartaIn", nCarta);
		doExecute(callableStatement);
	}

	public void insertCartaCredito(String nCarta, Calendar scadenza, int cvv,String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call insertCartaDiCredito(?, ?, ?)}");
		callableStatement.setString("ncartaIn", nCarta);
		callableStatement.setString("scadenzaIn", DateUtil.PrepTime(scadenza));
		callableStatement.setString("cvvIn", String.valueOf(cvv));
		doExecute(callableStatement);

	}

	public void register(String username, String eMail, String password, String nome, String cognome, String nTelefono, String userType, String passData) throws SQLException
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

	public boolean login(String username, String pass, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call loginUser(?, ?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("passwIn", pass);
		callableStatement.registerOutParameter("esito", Types.BOOLEAN);
		return doExecute(callableStatement).getBoolean("esito");
	}



	public void InsertCategoria(String nome, int quantita, int codiceaBarre,String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCategoria(?, ?, ?)}");
		callableStatement.setString("nomeIn", nome);
		callableStatement.setString("quantitaIn",String.valueOf(quantita));
		callableStatement.setString("codiceabarreIn",String.valueOf(codiceaBarre));
		doExecute(callableStatement);

	}
	public void InsertCompone(int nprodotti,String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCompone(?)}");
		callableStatement.setString("nprodottiIn", String.valueOf(nprodotti));
		doExecute(callableStatement);

	}
	public void InsertIndirizzo(String via,int ncivico,String citta, int cap, boolean flag, String username, String userType, String passData) throws SQLException {
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
	public void InsertOrdine(int id, int sconto, double totale,String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertOrdine(?, ?, ?)}");
		callableStatement.setString("idIn", String.valueOf(id));
		callableStatement.setString("quantitaIn", String.valueOf(sconto));
		callableStatement.setString("codiceabarreIn", String.valueOf(totale));
		doExecute(callableStatement);
	}
	public void InsertProdotto(int codiceaBarre, double prezzo, String descrizione,String specifiche, int quantita,String marca, String modello,String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertProdotto(?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("codiceabarreIn", String.valueOf(codiceaBarre));
		callableStatement.setString("prezzoIn", String.valueOf(prezzo));
		callableStatement.setString("descrizioneIn", descrizione);
		callableStatement.setString("specificheIn", specifiche);
		callableStatement.setString("quantitaIn", String.valueOf(quantita));
		callableStatement.setString("marcaIn", marca);
		callableStatement.setString("modelloIn", modello);
		doExecute(callableStatement);
	}




}
