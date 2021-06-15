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

	public Product(String codiceABarre, String marca, String modello, Double prezzo, String descrizione, String specifiche, String immagine,int quantitaProdotto, Calendar dataInserimento)
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
}