package Model.DAO;

import Model.CategoriesArray;
import Model.Category;
import Model.CrdGiver;
import Model.Product;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
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
	public static ArrayList<Product> callSelectProdottoByCategoria(String categoria, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectProdottoByCategoria(categoria, crd.getUsername(), crd.getPass());
		ArrayList<Product> resultsArray=new ArrayList<>();
		Product prodotto;
		while(result.next())
		{
			prodotto=new Product(
								result.getString("CODICEBARRE"),
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

	public static ArrayList<Category> callSelectCategoria(ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectCategoria(crd.getUsername(), crd.getPass());
		ArrayList<Category> categorie= new ArrayList<>();
		while(result.next())
		{
			categorie.add(new Category(result.getString("nome"), Integer.parseInt(result.getString("quantita"))));
		}
		connection.destroy();
		return categorie;
	}

	public static Map<String, Object> callSelectProdottoByCodiceABarre(String codiceABarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		Map<String, Object> risultati=connection.selectProdotto(codiceABarre, crd.getUsername(), crd.getPass());
		connection.destroy();
		return risultati;
	}

	public static void callInsertIndirizzo(ServletContext context, String via,int ncivico,String citta, int cap, boolean flag, String username) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertIndirizzo(via, ncivico, citta, cap, flag, username, crd.getUsername(), crd.getPass());
		connection.destroy();
	}

	public static void callInsertCartaDiCredito(ServletContext context, String username, String nCarta, Calendar scadenza, Integer cvv) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertCartaCredito(nCarta, scadenza, cvv, crd.getUsername(), crd.getPass());
		connection.updateUserCartaDiCredito(username, nCarta,crd.getUsername(), crd.getPass());
		connection.destroy();
	}

	public static int callRegister(ServletContext context, String username, String eMail, String password, String nome, String cognome, String nTelefono, String nCarta, Calendar scadenza, Integer cvv) throws SQLException, NoSuchAlgorithmException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		String passwordPronta=preparaPassword(password);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.register(username, eMail, passwordPronta, nome, cognome, nTelefono, crd.getUsername(), crd.getPass());
		if(nCarta!=null && scadenza!=null && cvv!=null)
		{
			connection.insertCartaCredito(nCarta, scadenza, cvv, crd.getUsername(), crd.getPass());
			connection.updateUserCartaDiCredito(username, nCarta,crd.getUsername(), crd.getPass());
		}
		else
		{
			//carta di credito non registrata
		}
		connection.destroy();
		return 0;
	}

	public static boolean callLogin(ServletContext context, String nickname, String password) throws NoSuchAlgorithmException, SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		String passwordInseritaHashed=preparaPassword(password);
		boolean b=connection.login(nickname, passwordInseritaHashed, crd.getUsername(), crd.getPass());
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