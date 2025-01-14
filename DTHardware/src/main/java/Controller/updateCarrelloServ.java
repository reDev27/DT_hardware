package Controller;

import Model.Carrello;
import Model.DAO.DateUtil;
import Model.Product;
import Model.ProductsArray;
import Model.RequestUtility;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Calendar;
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
		RequestUtility.checkIsLogged(request.getSession());
		HttpSession session=request.getSession();
		Carrello carrello= (Carrello) session.getAttribute("carrello");
		if(carrello==null)
			carrello=new Carrello();
		carrello.aggiornaCarrello(request);
		session.setAttribute("carrello", carrello);
	}
}