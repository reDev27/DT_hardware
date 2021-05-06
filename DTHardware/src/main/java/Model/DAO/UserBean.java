package Model.DAO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
		UserDAO connection=new UserDAO();
		return connection.doUpdate(query, "user", "Tav0l1n0");
	}

	public static String login(String nomeUtente, String password) throws NoSuchAlgorithmException, SQLException
	{
		UserDAO connection=new UserDAO();
		String passwordInseritaHashed=preparaPassword(password);
		String query= "select * from info where nomeUtente= \"" + nomeUtente + "\"" + " and pass=" + "\"" + passwordInseritaHashed + "\";";
		ResultSet result=connection.doStatement(query, "user", "Tav0l1n0");
		result.next();
		String s=result.getString("nomeUtente");
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
