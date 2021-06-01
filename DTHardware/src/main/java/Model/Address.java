package Model;

public class Address
{
	public Address(String via, int nCivico, String citta, int CAP, boolean isActive)
	{
		setVia(via);
		setnCivico(nCivico);
		setCitta(citta);
		setCAP(CAP);
		setActive(isActive);
	}


	private String via;
	private int nCivico;
	private String citta;
	private int CAP;
	private boolean isActive;

	public String getVia()
	{
		return via;
	}
	public void setVia(String via)
	{
		this.via = via;
	}
	public int getnCivico()
	{
		return nCivico;
	}
	public void setnCivico(int nCivico)
	{
		this.nCivico = nCivico;
	}
	public String getCitta()
	{
		return citta;
	}
	public void setCitta(String citta)
	{
		this.citta = citta;
	}
	public int getCAP()
	{
		return CAP;
	}
	public void setCAP(int CAP)
	{
		this.CAP = CAP;
	}
	public boolean isActive()
	{
		return isActive;
	}
	public void setActive(boolean active)
	{
		isActive = active;
	}
}
