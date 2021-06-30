package Model;

import Model.DAO.DateUtil;

import java.util.Calendar;
import java.util.Date;

public class CreditCard
{
	public CreditCard(String nCarta, Calendar scadenza, int CVV)
	{
		setnCarta(nCarta);
		setScadenza(scadenza);
		setCVV(CVV);
	}

	public String toString()
	{
		String nCartaCensored=nCarta.substring(0, 4) + "****" + nCarta.substring(7,11);
		return nCartaCensored + " " + DateUtil.getStringFromCalendar(scadenza).substring(0, 6);
	}

	private String nCarta;
	private Calendar scadenza;
	private int CVV;

	public String getnCarta()
	{
		return nCarta;
	}
	public void setnCarta(String nCarta)
	{
		this.nCarta = nCarta;
	}
	public Calendar getScadenza()
	{
		return scadenza;
	}
	public void setScadenza(Calendar scadenza)
	{
		this.scadenza = scadenza;
	}
	public int getCVV()
	{
		return CVV;
	}
	public void setCVV(int CVV)
	{
		this.CVV = CVV;
	}
}
