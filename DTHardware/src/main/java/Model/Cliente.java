package Model;

import Model.DAO.UserBean;
import Model.DAO.UserNotLoggedBean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Cliente
{
	public Cliente()
	{
		setAddresses(new ArrayList<>());
		setCreditCards(new ArrayList<>());
	}

	public Cliente(String username, String email, String nome, String cognome, String nTelefono, ArrayList<Address> addresses, ArrayList<CreditCard> creditCards)
	{
		setUsername(username);
		setEmail(email);
		setNome(nome);
		setCognome(cognome);
		setnTelefono(nTelefono);
		setAddresses(addresses);
		setCreditCards(creditCards);
	}

	/**
	 * "a" is for Admin, "l" is for del Logged user, "n" is for the Not logged user
	 * @param username the
	 * @param password
	 * @param context
	 * @param session isUsed to store the information of the authenticated user;
	 * @return true if is logged or false if not
	 * @throws SQLException
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 */
	public boolean authUser(String username, String password, ServletContext context, HttpSession session) throws SQLException, NoSuchAlgorithmException, IOException
	{
		boolean isLogged=false;
		if(username != null && password != null)
			isLogged=UserNotLoggedBean.callLogin(context, username, password);
		if(isLogged && username.compareTo("admin")==0)
			session.setAttribute("isLogged", "a");
		else if(isLogged)
			session.setAttribute("isLogged", "l");
		else
			session.setAttribute("isLogged", "n");
		return isLogged;
	}

	public void resizeArray(int addressIndex, int cardIndex)
	{
		if(addressIndex<0 || cardIndex<0)
			return;
		ArrayList<Address> addressesApp=new ArrayList<>();
		addressesApp.add(getAddresses().get(addressIndex));
		setAddresses(addressesApp);
		ArrayList<CreditCard> creditCardsApp=new ArrayList<>();
		creditCardsApp.add(creditCards.get(cardIndex));
		setCreditCards(creditCardsApp);
	}

	public ArrayList<Order> getOrdersByUsername(String username, ServletContext context) throws SQLException, IOException
	{
		return UserBean.callSelectOrderByUsername(username, context);
	}

	public ArrayList<Order> getOrdersByUsername(ServletContext context) throws SQLException, IOException
	{
		return UserBean.callSelectOrderByUsername(getUsername(), context);
	}

	private String username;
	private String email;
	private String nome;
	private String cognome;
	private String nTelefono;
	private ArrayList<Address> addresses;
	private ArrayList<CreditCard> creditCards;

	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public String getCognome()
	{
		return cognome;
	}
	public void setCognome(String cognome)
	{
		this.cognome = cognome;
	}
	public String getnTelefono()
	{
		return nTelefono;
	}
	public void setnTelefono(String nTelefono)
	{
		this.nTelefono = nTelefono;
	}
	public ArrayList<Address> getAddresses()
	{
		return addresses;
	}
	public void setAddresses(ArrayList<Address> addresses)
	{
		this.addresses = addresses;
	}
	public ArrayList<CreditCard> getCreditCards()
	{
		return creditCards;
	}
	public void setCreditCards(ArrayList<CreditCard> creditCards)
	{
		this.creditCards = creditCards;
	}
}
