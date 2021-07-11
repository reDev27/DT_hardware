package Model;

import Model.DAO.DateUtil;
import Model.DAO.UserBean;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Order
{
	public Order()
	{

	}

	public Order(double totale, Calendar dataAcquisto, String username)
	{
		setTotale(totale);
		setDataAcquisto(dataAcquisto);
		setUsername(username);
	}

	public Order(String fattura, double totale, Calendar dataAcquisto, String username)
	{
		setFattura(fattura);
		setTotale(totale);
		setDataAcquisto(dataAcquisto);
		setUsername(username);
	}

	public Order(int id, String fattura, double totale, Calendar dataAcquisto, String username, ProductsOfAnOrder products)
	{
		setId(id);
		setFattura(fattura);
		setTotale(totale);
		setDataAcquisto(dataAcquisto);
		setUsername(username);
		setProducts(products);
	}

	public Order(int id, String fattura, double totale, Calendar dataAcquisto, String username)
	{
		setId(id);
		setFattura(fattura);
		setTotale(totale);
		setDataAcquisto(dataAcquisto);
		setUsername(username);
	}

	public ArrayList<String> ordineEffettuato(Carrello carrello, ServletContext context) throws SQLException, IOException
	{
		ArrayList<String> prodottiEsauriti=new ArrayList<>();
		synchronized (this)
		{
			ArrayList<Product> products = carrello.getProdotti();
			try
			{
				UserBean.callInsertOrdine(getFattura(), totale, getDataAcquisto(), username, context);
				for (Product product : products)
				{
					if(UserBean.callInsertCompone(product.getQuantitaCarrello(), product.getCodiceABarre(), context))
						prodottiEsauriti.add(product.getCodiceABarre());
				}
			}
			catch (SQLException | IOException e)
			{
				e.printStackTrace();
			}
		}
		return prodottiEsauriti;
	}

	public String creaFattura(Cliente cliente, Carrello carrello)
	{
		String fattura = "Nome: " + cliente.getNome() + ";\nCognome: " + cliente.getCognome() + ";\nUsername: " + getUsername() + ";\nE-mail: " + cliente.getEmail() +
				";\nNumero di telefono: " + cliente.getnTelefono() + ";\nIndirizzo: " + cliente.getAddresses().get(0) + ";\nCarta di Credito: " +
				 cliente.getCreditCards().get(0) + ";\n\nCarrello:\n";
		ArrayList<Product> products=carrello.getProdotti();
		for(Product prodotto : products)
		{
			fattura += "Marca: " + prodotto.getMarca() + ", modello: " + prodotto.getModello() + ", prezzo: €" + prodotto.getPrezzo() + ", quantità: " +
					prodotto.getQuantitaCarrello() + ";\n";
		}
		//DecimalFormat decimalFormat=new DecimalFormat("#.##");
		fattura += "\nQuantità prodotti acquistati: " + carrello.getQuantitaTotaleCarrello() + ";\nSubtotale: &#x20AC " + carrello.getTotale() +
					";\nSpedizione: € 9.90;\nData di acquisto: " + DateUtil.getStringFromCalendar(getDataAcquisto()) + ";\nTotale: &#x20AC " + totale;
		setFattura(fattura);
		return getFattura();
	}

	private int id;
	private String fattura;
	private double totale;
	private Calendar dataAcquisto;
	private ProductsOfAnOrder products;
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
	public ProductsOfAnOrder getProducts()
	{
		return products;
	}
	public void setProducts(ProductsOfAnOrder products)
	{
		this.products = products;
	}
}
