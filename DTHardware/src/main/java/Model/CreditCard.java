package Model;

import java.util.Calendar;
import java.util.Date;

public class CreditCard
{
	public CreditCard(String nCarta, Calendar scadenza, int CVV)
	{
		setnCarta(nCarta);
		setScadenza(scadenza);
		setCVV(CVV);
		Date c;

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
