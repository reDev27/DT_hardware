package Controller;

import Model.Carrello;
import Model.DAO.DateUtil;
import Model.Product;
import Model.ProductsArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@WebServlet(name = "updateCarrelloServ", value = "/updateCarrelloServ")
public class updateCarrelloServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		String option= request.getParameter("option");
		Carrello carrello= (Carrello) session.getAttribute("carrello");
		if(carrello==null)
			carrello=new Carrello();
		if(option.compareToIgnoreCase("aggiungi")==0)
		{
			carrello.aggiungiProdotto(request);
		}
		else
		{
			carrello.eliminaProdotto(request.getParameter("codiceABarre"));
		}
		session.setAttribute("carrello", carrello);
	}
}