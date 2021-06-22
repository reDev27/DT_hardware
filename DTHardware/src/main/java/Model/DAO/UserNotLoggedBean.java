package Model.DAO;

import Model.Category;
import Model.CrdGiver;
import Model.Product;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

public class UserNotLoggedBean
{
	public static String callIsAvailableProduct(String codiceABarre, int quantita, ServletContext context) throws IOException, SQLException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		String codiceDaRestituire;
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.isAvailableProduct(codiceABarre, quantita, crd.getUsername(), crd.getPass());
		try
		{
			result.next();
			codiceDaRestituire=result.getString("codiceBarre");
		}
		catch (SQLException e)
		{
			codiceDaRestituire=null;
		}
		return codiceDaRestituire;
	}

	public static ArrayList<Product> callSelectMostRecentProducts(ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectMostRecentProducts(crd.getUsername(), crd.getPass());
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
					result.getString("IMMAGINE"),
					result.getInt("QUANTITA"),
					DateUtil.getCalendarFromString(result.getString("DATAINSERIMENTO"))
			);
			resultsArray.add(prodotto);
		}
		return resultsArray;
	}

	public static ArrayList<Product> callSelectProdottoByCategoria(String categoria, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectProdottoByCategoria(categoria,crd.getUsername(), crd.getPass());
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
								result.getString("IMMAGINE"),
								result.getInt("QUANTITA"),
								DateUtil.getCalendarFromString(result.getString("DATAINSERIMENTO"))
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

	public static Product callSelectProdottoByCodiceABarre(String codiceABarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result=connection.selectProdottoByCodiceABarre(codiceABarre, crd.getUsername(), crd.getPass());
		result.next();
		Product prodotto=new Product(
				result.getString("CODICEBARRE"),
				result.getString("DESCRIZIONE"),
				result.getString("SPECIFICHE"),
				result.getDouble("PREZZO"),
				result.getString("MARCA"),
				result.getString("MODELLO"),
				result.getString("IMMAGINE"),
				result.getInt("QUANTITA"),
				DateUtil.getCalendarFromString(result.getString("DATAINSERIMENTO"))
		);
		connection.destroy();
		return prodotto;
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

	private static String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}
}