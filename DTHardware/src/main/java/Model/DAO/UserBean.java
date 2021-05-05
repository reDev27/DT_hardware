package Model.DAO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBean
{

	public void register(String nickname, String eMail,String password, String nome, String cognome, String nTelefono, Date dataAcquisto, int nCarta) throws SQLException, NoSuchAlgorithmException
	{
		String passwordPronta=preparaPassword(password);
		String query="insert into cliente values ( \"" + nickname + "\"," + "\"" + passwordPronta + "\", 0.0);";
		UserDAO connection=new UserDAO();
		System.out.println("sono state modificate " + connection.doUpdate(query, "user", "Tav0lin0") + " righe");
	}

	private String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}

	public String login(String nomeUtente, String password) throws NoSuchAlgorithmException, SQLException
	{
		UserDAO connection=new UserDAO();
		String passwordInseritaHashed=preparaPassword(password);
		String query= "select * from info where nomeUtente= \"" + nomeUtente + "\"" + " and pass=" + "\"" + passwordInseritaHashed + "\";";
		ResultSet result=connection.doStatement(query, "user", "Tav0lin0");
		result.next();
		String s=result.getString("nomeUtente");
		return s;
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
