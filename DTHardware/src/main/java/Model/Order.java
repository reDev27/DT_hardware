package Model;

import Model.DAO.DateUtil;
import Model.DAO.UserBean;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

public class Order
{
	public Order()
	{
		setId(-1);
		setSconto(-1);
		setTotale(-1.0);
		setDataAcquisto(Calendar.getInstance());
		setUsername("none0123");
	}

	public Order(int id, int sconto, double totale, Calendar dataAcquisto, String username)
	{
		setId(id);
		setSconto(sconto);
		setTotale(totale);
		setDataAcquisto(dataAcquisto);
		setUsername(username);
	}

	public void ordineEffettuato(JsonObject obj, ServletContext context) throws SQLException, IOException
	{
		UserBean.callInsertOrdine(obj.get("id").getAsInt(), obj.get("sconto").getAsInt(), obj.get("totale").getAsDouble(), DateUtil.getCalendarFromString(obj.get("dataAcquisto").getAsString()), obj.get("username").getAsString(), context);
		UserBean.callInsertCompone(obj.get("nProdotti").getAsInt(), obj.get("id").getAsInt(), obj.get("codiceABarre").getAsString(), context);
	}



	private int id;
	private int sconto;
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
	public int getSconto()
	{
		return sconto;
	}
	public void setSconto(int sconto)
	{
		this.sconto = sconto;
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
