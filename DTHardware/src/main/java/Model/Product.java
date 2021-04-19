package Model;

import java.util.ArrayList;

public class Product
{
	public Product()
	{
		nome="senza nome";
		prezzo=0.0;
		disponibilita=false;
		descrizione=null;
		specifiche=null;
		immagini=null;
	}



	private String nome;
	private Double prezzo;
	private ArrayList<String> descrizione;
	private ArrayList<String> specifiche;
	private ArrayList<String> immagini;
	private boolean disponibilita;
	private int quantitaProdotto;

public int getQuantitaProdotto()
{
	return quantitaProdotto;
}
public void setQuantitaProdotto(int quantitaProdotto)
{
	this.quantitaProdotto = quantitaProdotto;
}
public String getNome()
{
	return nome;
}
public void setNome(String nome)
{
	this.nome = nome;
}
public Double getPrezzo()
{
	return prezzo;
}
public void setPrezzo(Double prezzo)
{
	this.prezzo = prezzo;
}
public ArrayList<String> getDescrizione()
{
	return descrizione;
}
public void setDescrizione(ArrayList<String> descrizione)
{
	descrizione = descrizione;
}
public ArrayList<String> getSpecifiche()
{
	return specifiche;
}
public void setSpecifiche(ArrayList<String> specifiche)
{
	specifiche = specifiche;
}
public ArrayList<String> getImmagini()
{
	return immagini;
}
public void setImmagini(ArrayList<String> immagini)
{
	this.immagini = immagini;
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