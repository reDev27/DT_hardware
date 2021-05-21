package Model.DAO;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

public class UserNotLoggedBean
{
	public static Map<String, Object> callSelectProdotto(String codiceABarre) throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		Map<String, Object> risultati=connection.selectProdotto(codiceABarre, "root", "aaaa");


		/*
		Blob immagine=(Blob) risultati.get("immagineOut");
		File f=new File("C:\\Users\\rEDOx\\Desktop\\Nuova cartella\\image.txt");
		f.createNewFile();
		PrintStream printStream=new PrintStream(f);
		InputStream in = immagine.getBinaryStream();
		int length = (int) immagine.length();
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];
		while ((length = in.read(buffer)) != -1) {
			//out.write(buffer, 0, length);
			printStream.write(buffer, 0, length);
		}*/
		connection.destroy();
		return risultati;
	}

	public static void callInsertIndirizzo(String via,int ncivico,String citta, int cap, boolean flag, String username) throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertIndirizzo(via, ncivico, citta, cap, flag, username, "root", "aaaa");
		connection.destroy();
	}

	public static void callInsertCartaDiCredito(String username, String nCarta, Calendar scadenza, Integer cvv) throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertCartaCredito(nCarta, scadenza, cvv, "user", "Tav0l1n0");
		connection.updateUserCartaDiCredito(username, nCarta,"user", "Tav0l1n0");
		connection.destroy();
	}

	public static int callRegister(String username, String eMail, String password, String nome, String cognome, String nTelefono, String nCarta, Calendar scadenza, Integer cvv) throws SQLException, NoSuchAlgorithmException
	{
		String passwordPronta=preparaPassword(password);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.register(username, eMail, passwordPronta, nome, cognome, nTelefono, "user", "Tav0l1n0");
		if(nCarta!=null && scadenza!=null && cvv!=null)
		{
			connection.insertCartaCredito(nCarta, scadenza, cvv, "user", "Tav0l1n0");
			connection.updateUserCartaDiCredito(username, nCarta,"user", "Tav0l1n0");
		}
		else
		{
			//carta di credito non registrata
		}
		connection.destroy();
		return 0;
	}

	public static boolean callLogin(String nickname, String password) throws NoSuchAlgorithmException, SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		String passwordInseritaHashed=preparaPassword(password);
		boolean b=connection.login(nickname, passwordInseritaHashed, "user", "Tav0l1n0");
		connection.destroy();
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