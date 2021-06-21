package Model;

import Model.DAO.DateUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

public class Carrello extends ProductsArray
{
	public Carrello()
	{
		super();
	}

	public void aggiungiProdotto(HttpServletRequest request)
	{
		HttpSession session=request.getSession();
		Product product=new Product
				(
						request.getParameter("codiceABarre"),
						request.getParameter("descrizione"),
						request.getParameter("specifiche"),
						Double.parseDouble(request.getParameter("prezzo")),
						request.getParameter("marca"),
						request.getParameter("modello"),
						request.getParameter("immagine"),
						Integer.parseInt(request.getParameter("quantitaProdotto")),
						DateUtil.getCalendarFromString(request.getParameter("dataInserimento"))
				);
		getProdotti().add(product);
	}

	public void eliminaProdotto(String id)
	{
		if(id==null)
			return;
		ArrayList<Product> products=getProdotti();
		int i=0;
		for(Product product: products)
		{
			if(id.compareToIgnoreCase(product.getCodiceABarre())==0)
			{
				products.remove(i);
				break;
			}
			i++;
		}
	}
}
