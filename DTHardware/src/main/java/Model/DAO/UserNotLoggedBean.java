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

public class UserNotLoggedBean
{
	public static ArrayList<Product> callSearchProductsRedirect(String toSearch, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd = new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection = new UserNotLoggedDAO();
		ResultSet result = connection.searchProdotto(toSearch, crd.getUsername(), crd.getPass());
		ResultSet resultTableProducts;
		ArrayList<Product> products=new ArrayList<>();
		while(result.next())
		{
			resultTableProducts=connection.selectProdottoByCodiceABarre(result.getString("codicebarre"), crd.getUsername(),crd.getPass());
			resultTableProducts.next();
			products.add
					(
							new Product(
									resultTableProducts.getString("CODICEBARRE"),
									resultTableProducts.getString("DESCRIZIONE"),
									resultTableProducts.getString("SPECIFICHE"),
									resultTableProducts.getDouble("PREZZO"),
									resultTableProducts.getString("MARCA"),
									resultTableProducts.getString("MODELLO"),
									resultTableProducts.getString("IMMAGINE"),
									resultTableProducts.getInt("QUANTITA"),
									DateUtil.getCalendarFromString(resultTableProducts.getString("DATAINSERIMENTO"))
							)
					);
		}
		connection.destroy();
		return products;
	}

	public static ArrayList<Product> callSearchProducts(String toSearch, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd = new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection = new UserNotLoggedDAO();
		ResultSet result = connection.searchProdotto(toSearch, crd.getUsername(), crd.getPass());
		ArrayList<Product> products=new ArrayList<>();
		while(result.next())
		{
			products.add
					(
							new Product(
									result.getString("codicebarre"),
									result.getString("marca"),
									result.getString("modello")
							)
					);
		}
		return products;
	}

	public static boolean callSelectMail(String email, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result = connection.selectEmail(email, crd.getUsername(), crd.getPass());
		boolean esito=false;
		try
		{
			result.next();
			result.getString("email");
		}
		catch(SQLException e)
		{
			esito = true;
		}
		connection.destroy();
		return esito;
	}

	public static boolean callSelectUsername(String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		ResultSet result = connection.selectUsername(username, crd.getUsername(), crd.getPass());
		boolean esito=false;
		try
		{
			result.next();
			result.getString("username");
		}
		catch(SQLException e)
		{
			esito = true;
		}
		connection.destroy();
		return esito;
	}

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
		connection.destroy();
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
		connection.destroy();
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

	public static void callInsertIndirizzo(ServletContext context, String via,int ncivico,String citta, int cap, int flag, String username) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		try
		{
			connection.insertIndirizzo(via, ncivico, citta, cap, flag, crd.getUsername(), crd.getPass());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		callInsertRisiede(via, ncivico, username, context);
		connection.destroy();
	}

	public static void callInsertRisiede(String via, int ncivico, String username, ServletContext context) throws IOException, SQLException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertRisiede(via, ncivico, username, crd.getUsername(), crd.getPass());
		connection.destroy();
	}

	public static void callInsertCartaDiCredito(ServletContext context, String username, String nCarta, Calendar scadenza, Integer cvv) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertCartaCredito(nCarta, scadenza, cvv, username,crd.getUsername(), crd.getPass());
		connection.destroy();
	}

/*	TODO Questa è la vecchia implementazione, da cancellare
	public static void callRegister(ServletContext context, String username, String eMail, String password, String nome, String cognome, String nTelefono, String nCarta, Calendar scadenza, Integer cvv) throws SQLException, NoSuchAlgorithmException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(2);
		String passwordPronta=preparaPassword(password);
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.register(username, eMail, passwordPronta, nome, cognome, nTelefono, crd.getUsername(), crd.getPass());
		if(nCarta!=null && scadenza!=null && cvv!=null)
		{
			connection.insertCartaCredito(nCarta, scadenza, cvv, username,crd.getUsername(), crd.getPass());
		}
		connection.destroy();
	}*/

	public static void callRegister(ServletContext context, String username, String eMail, String password, String nome, String cognome, String nTelefono, String nCarta, Calendar scadenza, Integer cvv)
			throws SQLException, NoSuchAlgorithmException, IOException {

		//faccio la validazione sugli input
		if (context == null) {
			throw new IllegalArgumentException("Context must not be null");
		}
		if (username == null || username.length() > 30) {
			throw new IllegalArgumentException("Invalid username: must be non-null and at most 30 characters");
		}
		if (eMail == null || !eMail.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
			throw new IllegalArgumentException("Invalid email format");
		}
		if (password == null || password.length() < 8) {
			throw new IllegalArgumentException("Invalid password: must be at least 8 characters");
		}
		if (nome == null || !nome.matches("^\\b[A-Z][a-z]*$")) {
			throw new IllegalArgumentException("Invalid name format: must start with a capital letter followed by lowercase letters");
		}
		if (cognome == null || !cognome.matches("^\\b[A-Z][a-z]*$")) {
			throw new IllegalArgumentException("Invalid surname format: must start with a capital letter followed by lowercase letters");
		}
		if (nTelefono == null || !nTelefono.matches("^\\d{10}$")) {
			throw new IllegalArgumentException("Invalid phone number: must be exactly 10 digits");
		}

		// Validazione opzionale per i dati della carta di credito
		if (nCarta != null) {
			if (nCarta.length() != 12) {
				throw new IllegalArgumentException("Invalid credit card number: must be 12 digits");
			}
			if (scadenza == null) {
				throw new IllegalArgumentException("Expiration date must not be null when a credit card is provided");
			}
			if (cvv == null || cvv <= 100 || cvv >= 999) {
				throw new IllegalArgumentException("Invalid CVV: must be a 3-digit number");
			}
		}

		// se i valori in input sono giusti faccio l'autenticazione
		CrdGiver crd = new CrdGiver(context);
		crd.aggiornaCrd(2);

		String passwordPronta = preparaPassword(password);
		UserNotLoggedDAO connection = new UserNotLoggedDAO();	//autenticazione anche sul database

		//eseguo la registrazione
		connection.register(username, eMail, passwordPronta, nome, cognome, nTelefono, crd.getUsername(), crd.getPass());

		//TODO questo codice ad ora non viene mai usato, da fare refactor
		if (nCarta != null && scadenza != null && cvv != null) {
			connection.insertCartaCredito(nCarta, scadenza, cvv, username, crd.getUsername(), crd.getPass());
		}

		//chiudo la connessione con il database
		connection.destroy();
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

	protected static String preparaPassword(String password) throws NoSuchAlgorithmException
	{
		MessageDigest digest=MessageDigest.getInstance("SHA-1");
		digest.reset();
		digest.update(password.getBytes(StandardCharsets.UTF_8));
		return String.format("%040x", new BigInteger(1, digest.digest()));
	}
}