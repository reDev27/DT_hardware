package Model;

import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class Order
{
	public Order()
	{

	}

	public Order(String fattura, double totale, Calendar dataAcquisto, String username)
	{
		setFattura(fattura);
		setTotale(totale);
		setDataAcquisto(dataAcquisto);
		setUsername(username);
	}

	public void ordineEffettuato(ServletContext context) throws SQLException, IOException
	{

	}



	private int id;
	private String fattura;
	private double totale;
	private Calendar dataAcquisto;
	private String username;

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	public String getFattura()
	{
		return fattura;
	}
	public void setFattura(String fattura)
	{
		this.fattura = fattura;
	}
	public double getTotale()
	{
		return totale;
	}
	public void setTotale(double totale)
	{
		this.totale = totale;
	}
	public Calendar getDataAcquisto()
	{
		return dataAcquisto;
	}
	public void setDataAcquisto(Calendar dataAcquisto)
	{
		this.dataAcquisto = dataAcquisto;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
}
