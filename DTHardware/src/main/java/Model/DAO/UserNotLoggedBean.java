package Model.DAO;

import Model.CategorieArray;
import Model.Product;

import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class UserNotLoggedBean
{
	public static ArrayList<Product> callSelectProdottoByCategoria(String categoria) throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectProdottoByCategoria(categoria, "user", "Tav0l1n0");
		ArrayList<Product> resultsArray=new ArrayList<>();
		Product prodotto;
		while(result.next())
		{
			prodotto=new Product(
								result.getString("CODICEABARRE"),
								result.getString("DESCRIZIONE"),
								result.getString("SPECIFICHE"),
								result.getDouble("PREZZO"),
								result.getString("MARCA"),
								result.getString("MODELLO"),
								result.getBlob("IMMAGINE"),
								result.getInt("QUANTITA")
								);
			resultsArray.add(prodotto);
		}
		return resultsArray;
	}

	public static CategorieArray callSelectCategoria() throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectCategoria("user", "Tav0l1n0");
		CategorieArray categorie=new CategorieArray();
		while(result.next())
		{
			categorie.addCategoria(result.getString("nome"), Integer.parseInt(result.getString("quantita")));
		}
		connection.destroy();
		return categorie;
	}

	public static Map<String, Object> callSelectProdottoByCodiceABarre(String codiceABarre) throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		Map<String, Object> risultati=connection.selectProdotto(codiceABarre, "root", "aaaa");
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

	public static String getBase64Image(Blob image) throws IOException
	{
		InputStream in=null;
		int length = 0;
		try
		{
			in=image.getBinaryStream();
			length = (int) image.length();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		byte[] immagine=new byte[200000];
		int i;
		while ((i = in.read(immagine, 0, length)) != -1)
		{
			output.write(immagine, 0, i);
		}
		String encoded = Base64.getEncoder().encodeToString(immagine);
		output.flush();
		output.close();
		return encoded;
	}

	private static String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}
}