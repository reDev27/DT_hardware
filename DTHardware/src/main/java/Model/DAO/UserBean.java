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

public class UserBean
{
	public static void fileWrite(String genericURL) throws IOException
	{
		File f=new File(genericURL + "/src/java/model/userPass");
		f.createNewFile();
		PrintWriter printWriter=new PrintWriter(f);
		printWriter.write("user, Tav0l1n0");
	}

	public static Blob callSelectProdotto(String codiceABarre) throws SQLException
	{
		UserDAO connection=new UserDAO();
		Map<String, Object> risultati=connection.selectProdotto(codiceABarre, "root", "aaaa");
		Blob immagine=(Blob) risultati.get("immagineOut");

		/*File f=new File("C:\\Users\\rEDOx\\Desktop\\Nuova cartella\\image.txt");
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
		return immagine;
	}

	public static void callInsertProdotto(String codiceABarre, double prezzo, String descrizione,String specifiche, int quantita, String marca, String modello) throws SQLException, FileNotFoundException
	{
		UserDAO connection=new UserDAO();
		connection.insertProdotto(codiceABarre, prezzo, descrizione, specifiche, preparaImmagine("D:\\file_miei\\immagini\\disegno dove mostro le mie impareggiabli doti artistiche da disegnatore e grafico 4d iper ultra.png"),quantita, marca, modello,"root", "aaaa");
		connection.destroy();
	}

	public void callInsertOrdine(int id, int sconto, double totale) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertOrdine(id, sconto, totale, "root", "aaaa");
		connection.destroy();
	}

	public void callInsertIndirizzo(String via,int ncivico,String citta, int cap, boolean flag, String username) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertIndirizzo(via, ncivico, citta, cap, flag, username, "root", "aaaa");
		connection.destroy();
	}

	public void callInsertCategoria(String nome, int quantita, int codiceaBarre) throws SQLException  ////////////
	{
		UserDAO connection=new UserDAO();
		connection.insertCategoria(nome, quantita, codiceaBarre, "root", "aaaa");
	}

	public void callInsertCompone(int nprodotti, int id, String codiceABarre) throws SQLException /////////
	{
		UserDAO connection=new UserDAO();
		connection.insertCompone(nprodotti, id, codiceABarre, "root", "aaaa");
		connection.destroy();
	}

	public static void callInsertCartaDiCredito(String username, String nCarta, Calendar scadenza, Integer cvv) throws SQLException
	{
		UserDAO connection=new UserDAO();
		connection.insertCartaCredito(nCarta, scadenza, cvv, "user", "Tav0l1n0");
		connection.updateUserCartaDiCredito(username, nCarta,"user", "Tav0l1n0");
		connection.destroy();
	}

	public static int callRegister(String username, String eMail, String password, String nome, String cognome, String nTelefono, String nCarta, Calendar scadenza, Integer cvv) throws SQLException, NoSuchAlgorithmException
	{
		String passwordPronta=preparaPassword(password);
		UserDAO connection=new UserDAO();
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
		UserDAO connection=new UserDAO();
		String passwordInseritaHashed=preparaPassword(password);
		boolean b=connection.login(nickname, passwordInseritaHashed, "user", "Tav0l1n0");
		connection.destroy();
		return b;
	}

	private static InputStream preparaImmagine(String path) throws FileNotFoundException
	{
		File file=new File(path);
		FileInputStream stream=new FileInputStream(file);
		return (InputStream) stream;
	}

	private static String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}
}
