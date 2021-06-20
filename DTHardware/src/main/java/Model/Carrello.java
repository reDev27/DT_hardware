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
		Carrello carrello= (Carrello) session.getAttribute("carrello");
		if(carrello==null)
			carrello=new Carrello();
		carrello.getProdotti().add(product);
		session.setAttribute("carrello", carrello);
	}

	public void eliminaProdotto(String id, HttpSession session)
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
		Carrello carrello= (Carrello) session.getAttribute("carrello");
		if(carrello!=null)
			session.setAttribute("carrello", carrello);
	}
}
