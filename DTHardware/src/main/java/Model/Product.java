package Model;

import Model.DAO.DateUtil;
import Model.DAO.UserNotLoggedBean;

import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Map;

public class Product
{
	public Product()
	{

	}

	public Product(String codiceABarre, String marca, String modello)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
	}

	public Product(String codiceABarre, double prezzo, String immagine, int quantitaProdotto, String marca, String modello)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
		setPrezzo(prezzo);
		setImmagine(immagine);
		setDisponibilita(quantitaProdotto > 0);
		setQuantitaProdotto(quantitaProdotto);
	}

	public Product(String codiceABarre, String descrizione, String specifiche, Double prezzo, String marca, String modello, String immagine, int quantitaProdotto, Calendar dataInserimento)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
		setPrezzo(prezzo);
		setDescrizione(descrizione);
		setSpecifiche(specifiche);
		setImmagine(immagine);
		setDisponibilita(quantitaProdotto > 0);
		setQuantitaProdotto(quantitaProdotto);
		setDataInserimento(dataInserimento);
	}

	public Product(String codiceABarre, String descrizione, String specifiche, Double prezzo, String marca, String modello, String immagine, int quantitaProdotto, Calendar dataInserimento, int quantitaCarrello)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
		setPrezzo(prezzo);
		setDescrizione(descrizione);
		setSpecifiche(specifiche);
		setImmagine(immagine);
		setDisponibilita(quantitaProdotto > 0);
		setQuantitaProdotto(quantitaProdotto);
		setDataInserimento(dataInserimento);
		setQuantitaCarrello(quantitaCarrello);
	}

	public Product(String codiceABarre, String descrizione, String specifiche, Double prezzo, String marca, String modello, String immagine, int quantitaProdotto, Calendar dataInserimento, String nomeCategoria)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
		setPrezzo(prezzo);
		setDescrizione(descrizione);
		setSpecifiche(specifiche);
		setImmagine(immagine);
		setDisponibilita(quantitaProdotto > 0);
		setQuantitaProdotto(quantitaProdotto);
		setDataInserimento(dataInserimento);
		setNomeCategoria(nomeCategoria);
	}

	public Product(String codiceABarre, String descrizione, String specifiche, Double prezzo, String marca, String modello, String immagine, int quantitaProdotto, String nomeCategoria)
	{
		setCodiceABarre(codiceABarre);
		setMarca(marca);
		setModello(modello);
		setPrezzo(prezzo);
		setDescrizione(descrizione);
		setSpecifiche(specifiche);
		setImmagine(immagine);
		setDisponibilita(quantitaProdotto > 0);
		setQuantitaProdotto(quantitaProdotto);
		setNomeCategoria(nomeCategoria);
	}

/**
 * this function set the instance variables of THIS object with the result retried by database
 * @param codiceABarre string used to identify the product of you want to know attributes, is the code that identify the product
 */
	public void getProductByCodiceABarre(String codiceABarre, ServletContext context) throws SQLException, IOException
	{
		UserNotLoggedBean.callSelectProdottoByCodiceABarre(codiceABarre, context);
		setDisponibilita(quantitaProdotto > 0);
	}

	private String codiceABarre;
	private String marca;
	private String modello;
	private Double prezzo;
	private String descrizione;
	private String specifiche;
	private String immagine;
	private boolean disponibilita;
	private int quantitaProdotto;
	private Calendar dataInserimento;
	private int quantitaCarrello;
	private String nomeCategoria;

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
	this.descrizione = descrizione;
}
public String getSpecifiche()
{
	return specifiche;
}
public void setSpecifiche(String specifiche)
{
	this.specifiche = specifiche;
}
public String getImmagine()
{
	return immagine;
}
public void setImmagine(String immagine)
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
public Calendar getDataInserimento()
{
	return dataInserimento;
}
public void setDataInserimento(Calendar dataInserimento)
{
	this.dataInserimento = dataInserimento;
}
public int getQuantitaCarrello()
{
	return quantitaCarrello;
}
public void setQuantitaCarrello(int quantitaCarrello)
{
	this.quantitaCarrello = quantitaCarrello;
}
public String getNomeCategoria()
{
	return nomeCategoria;
}
public void setNomeCategoria(String nomeCategoria)
{
	this.nomeCategoria = nomeCategoria;
}
}