package Model;

import java.util.ArrayList;

public class Cliente
{
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




	private String username;
	private String email;
	private String nome;
	private String Cognome;
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
		return Cognome;
	}
	public void setCognome(String cognome)
	{
		Cognome = cognome;
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
