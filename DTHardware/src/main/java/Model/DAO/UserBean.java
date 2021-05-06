package Model.DAO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class UserBean
{

	public static int register(String nickname, String eMail, String password, String nome, String cognome, String nTelefono, Calendar dataAcquisto, int nCarta, Calendar scadenza, int cvv) throws SQLException, NoSuchAlgorithmException
	{
		String passwordPronta=preparaPassword(password);
		String query="call registerUser(\""+nickname+"\",\""+eMail+"\",\""+passwordPronta+"\",\""+nome+"\",\""+cognome+"\","+nTelefono+",\""+dateUtil.PrepTime(dataAcquisto)+"\","+nCarta+",\""+dateUtil.PrepTime(dataAcquisto)+"\","+ cvv +");";
		System.out.println(query);
		UserDAO connection=new UserDAO();
		return connection.doUpdate(query, "root", "b3ta");
	}

	public static String login(String nickname, String password) throws NoSuchAlgorithmException, SQLException
	{

		UserDAO connection=new UserDAO();
		String passwordInseritaHashed=preparaPassword(password);
		String query= "call loginUser(\""+nickname+"\", \""+passwordInseritaHashed+"\", @esito);";
		ResultSet result=connection.doExecute(query, "root", "b3ta");
		//result.next();
		String s=String.valueOf(result.getInt(3));//result.getString("@esito");
		return s;
	}

	private static String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}

	/*private User utente;

	public User getUtente()
	{
		return utente;
	}
	public void setUtente(User utente)
	{
		this.utente = utente;
	}*/
}
