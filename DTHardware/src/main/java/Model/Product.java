package Model;

import java.sql.Blob;

public class Product
{
	public Product()
	{
		setCodiceABarre(null);
		setMarca("senza nome");
		setModello("senza nome");
		setPrezzo(0.0);
		setDescrizione(null);
		setSpecifiche(null);
		setImmagine(null);
		setDisponibilita(false);
		setQuantitaProdotto(0);
	}

	public Product(String codiceABarre, String marca, String modello, Double prezzo, String descrizione, String specifiche, Blob immagine, boolean disponibilita,int quantitaProdotto)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
		setPrezzo(prezzo);
		setDescrizione(descrizione);
		setSpecifiche(specifiche);
		setImmagine(immagine);
		setDisponibilita(disponibilita);
		setQuantitaProdotto(quantitaProdotto);
	}

	public void getProduct(String codiceABarre)
	{

	}


	private String codiceABarre;
	private String marca;
	private String modello;
	private Double prezzo;
	private String descrizione;
	private String specifiche;
	private Blob immagine;
	private boolean disponibilita;
	private int quantitaProdotto;

public String getMarca()
{
	return marca;
}
public void setMarca(String marca)
{
	this.marca = marca;
}
public String getCodiceABarre()
{
	return codiceABarre;
}
public void setCodiceABarre(String codiceABarre)
{
	this.codiceABarre = codiceABarre;
}
public int getQuantitaProdotto()
{
	return quantitaProdotto;
}
public void setQuantitaProdotto(int quantitaProdotto)
{
	this.quantitaProdotto = quantitaProdotto;
}
public String getModello()
{
	return modello;
}
public void setModello(String modello)
{
	this.modello = modello;
}
public Double getPrezzo()
{
	return prezzo;
}
public void setPrezzo(Double prezzo)
{
	this.prezzo = prezzo;
}
public String getDescrizione()
{
	return descrizione;
}
public void setDescrizione(String descrizione)
{
	descrizione = descrizione;
}
public String getSpecifiche()
{
	return specifiche;
}
public void setSpecifiche(String specifiche)
{
	specifiche = specifiche;
}
public Blob getImmagine()
{
	return immagine;
}
public void setImmagine(Blob immagine)
{
	this.immagine = immagine;
}
public boolean isDisponibilita()
{
	return disponibilita;
}
public void setDisponibilita(boolean disponibilita)
{
	this.disponibilita = disponibilita;
}
}