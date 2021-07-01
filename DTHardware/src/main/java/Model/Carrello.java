package Model;

import Model.DAO.DateUtil;
import Model.DAO.UserNotLoggedBean;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Carrello extends ProductsArray
{
	public Carrello()
	{
		super();
	}

	public ArrayList<String> verifyAvailability(ServletContext context) throws SQLException, IOException
	{
		ArrayList<Product> products=getProdotti();
		ArrayList<String> codiciProdottiEsauriti=new ArrayList<>();
		String appoggio=null;
		for(Product product: products)
		{
			appoggio=UserNotLoggedBean.callIsAvailableProduct(product.getCodiceABarre(), product.getQuantitaCarrello(), context);
			if(appoggio!=null)
				codiciProdottiEsauriti.add(appoggio);
			appoggio=null;
		}
		return codiciProdottiEsauriti;
	}

	public int getQuantitaTotaleCarrello()
	{
		ArrayList<Product> products=getProdotti();
		int totale=0;
		for(Product product : products)
		{
			totale += product.getQuantitaCarrello();
		}
		return totale;
	}

	public double getTotale()
	{
		ArrayList<Product> products=getProdotti();
		double totale=0;
		for(Product product: products)
		{
			totale+=product.getQuantitaCarrello()*product.getPrezzo();
		}
		return totale;
	}

	public void aggiornaCarrello(HttpServletRequest request)
	{
		String codiceABarre=request.getParameter("codiceABarre");
		ArrayList<Product> products=getProdotti();
		Product prodotto;
		int size=products.size();
		for(int i=0; i<size; i++)
		{
			prodotto=products.get(i);
			int n=Integer.parseInt(request.getParameter("quantitaCarrello"));
			if(prodotto.getCodiceABarre().compareTo(codiceABarre)==0)
			{
				if(n!=0)
					prodotto.setQuantitaCarrello(n + prodotto.getQuantitaCarrello());
				else
					products.remove(i);
				return;
			}
		}
		Product product=new Product
				(
						codiceABarre,
						request.getParameter("descrizione"),
						request.getParameter("specifiche"),
						Double.parseDouble(request.getParameter("prezzo")),
						request.getParameter("marca"),
						request.getParameter("modello"),
						request.getParameter("immagine"),
						Integer.parseInt(request.getParameter("quantitaProdotto")),
						DateUtil.getCalendarFromString(request.getParameter("dataInserimento")),
						Integer.parseInt(request.getParameter("quantitaCarrello"))
				);
		getProdotti().add(product);
	}
}
