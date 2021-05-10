package Model.DAO;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Calendar;

public class UserBean
{

	public static int callRegister(String username, String eMail, String password, String nome, String cognome, String nTelefono, Calendar dataAcquisto, String nCarta, Calendar scadenza, int cvv) throws SQLException, NoSuchAlgorithmException
	{
		String passwordPronta=preparaPassword(password);
		//String query="call registerUser(\""+nickname+"\",\""+eMail+"\",\""+passwordPronta+"\",\""+nome+"\",\""+cognome+"\","+nTelefono+",\""+dateUtil.PrepTime(dataAcquisto)+"\","+nCarta+",\""+dateUtil.PrepTime(dataAcquisto)+"\","+ cvv +");";
		UserDAO connection=new UserDAO();
		connection.register(username, eMail, passwordPronta, nome, cognome, nTelefono, dataAcquisto, nCarta, scadenza, cvv, "user", "Tav0l1n0");
		return 0;
	}

	public static boolean callLogin(String nickname, String password) throws NoSuchAlgorithmException, SQLException
	{
		UserDAO connection=new UserDAO();
		String passwordInseritaHashed=preparaPassword(password);
		boolean b=connection.login(nickname, passwordInseritaHashed, "user", "Tav0l1n0");
		return b;
	}

	private static String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}
}
