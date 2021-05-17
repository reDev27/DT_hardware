package Model.DAO;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

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

	public void insertCategoria(String nome, int quantita, int codiceaBarre,String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCategoria(?, ?, ?)}");
		callableStatement.setString("nomeIn", nome);
		callableStatement.setString("quantitaIn",String.valueOf(quantita));
		callableStatement.setString("codiceabarreIn",String.valueOf(codiceaBarre));
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
	public void insertIndirizzo(String via,int ncivico,String citta, int cap, boolean flag, String username, String userType, String passData) throws SQLException {
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
	public void insertOrdine(int id, int sconto, double totale,String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertOrdine(?, ?, ?)}");
		callableStatement.setString("idIn", String.valueOf(id));
		callableStatement.setString("quantitaIn", String.valueOf(sconto));
		callableStatement.setString("codiceabarreIn", String.valueOf(totale));
		doExecute(callableStatement);
	}

	public void insertProdotto(String codiceaBarre, double prezzo, String descrizione, String specifiche, InputStream image, int quantita, String marca, String modello, String userType, String passData) throws SQLException {
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertProdotto(?, ?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("codiceabarreIn", codiceaBarre);
		callableStatement.setString("prezzoIn", String.valueOf(prezzo));
		callableStatement.setString("descrizioneIn", descrizione);
		callableStatement.setString("specificheIn", specifiche);
		callableStatement.setBinaryStream("immagineIn", image);
		callableStatement.setString("quantitaIn", String.valueOf(quantita));
		callableStatement.setString("marcaIn", marca);
		callableStatement.setString("modelloIn", modello);
		doExecute(callableStatement);
	}

	public Map<String, Object> selectProdotto(String codiceABarre, String userType, String passData) throws SQLException
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

}
