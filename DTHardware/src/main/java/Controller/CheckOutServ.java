package Controller;

import Model.Carrello;
import Model.Cliente;
import Model.Order;
import Model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(name = "CheckOutServ", value = "/CheckOutServ")
public class CheckOutServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		Calendar ora=Calendar.getInstance();
		ArrayList<String> prodottiEsauriti=null;
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			Carrello carrello = (Carrello) session.getAttribute("carrello");
			Order order = new Order(carrello.getTotale() + 9.90, ora, (String) session.getAttribute("user"));
			order.creaFattura(cliente, carrello);
			try
			{
				prodottiEsauriti=order.ordineEffettuato(carrello, request.getServletContext());
			}
			catch (SQLException | IOException throwables)
			{
				throwables.printStackTrace();
			}
			if(prodottiEsauriti!=null)
			{
				if(prodottiEsauriti.size()>0)
				{
					ArrayList<Product> prodottiEsauritiList=new ArrayList<>();
					for(String prodotto:prodottiEsauriti)
					{
						prodottiEsauritiList.add(new Product(prodotto));
					}
					Gson gson=new Gson();
					response.getWriter().write(gson.toJson(prodottiEsauritiList));
					throw new Error();
				}
				else
					session.removeAttribute("carrello");
			}
		}
		else
			throw new Error();
	}
}