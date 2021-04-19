package Model;

import java.util.ArrayList;

public class Carrello
{
	public Carrello()
	{
		prodotti = new ArrayList<Product>();
		numeroProdotti=0;
		subTotale=0.0;
		tasse =0.0;
		totale=0.0;
	}

	public Double addTaxes()
	{
		tasse += tasse*22/100;
		aggiornaPrezzo();
		return tasse;
	}

	public Double addTaxes(ArrayList<Double> daAggiungere)
	{
		for(Double parziale : daAggiungere)
		{
			tasse+=parziale;
		}
		aggiornaPrezzo();
		return tasse;
	}

	public Double addTaxes(Double daAggiungere)
	{
		tasse+=daAggiungere;
		aggiornaPrezzo();
		return tasse;
	}

	public Double doSumSubTotale()
	{
		for(Product prodotto : prodotti)
		{
			subTotale += prodotto.getPrezzo();
		}
		aggiornaPrezzo();
		return subTotale;
	}

	public Double aggiornaPrezzo()
	{
		return totale=subTotale+tasse;
	}

	private ArrayList<Product> prodotti;
	private int numeroProdotti;
	private Double subTotale;
	private Double totale;
	private Double tasse;

	public ArrayList<Product> getProducts()
	{
		return prodotti;
	}
	public void setProducts(ArrayList<Product> products)
{
	this.prodotti = products;
}
	public int getProductCount()
{
	return numeroProdotti;
}
	public void setProductCount(int productCount)
{
	this.numeroProdotti = productCount;
}
public Double getSubTotale()
{
	return subTotale;
}
public void setSubTotale(Double subTotale)
{
	this.subTotale = subTotale;
}
public Double getTotale()
{
	return totale;
}
public void setTotale(Double totale)
{
	this.totale = totale;
}
public Double getTasse()
{
	return tasse;
}
public void setTasse(Double tasse)
{
	this.tasse = tasse;
}
}
