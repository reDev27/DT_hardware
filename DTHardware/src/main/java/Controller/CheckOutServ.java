package Controller;

import Model.Carrello;
import Model.Cliente;
import Model.Order;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "CheckOutServ", value = "/CheckOutServ")
public class CheckOutServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		Cliente cliente= (Cliente) session.getAttribute("cliente");
		Carrello carrello= (Carrello) session.getAttribute("carrello");
		String fattura=null;
		Order order=new Order(fattura, carrello.getTotale()+9.90, Calendar.getInstance(), (String) session.getAttribute("user"));
	}
}