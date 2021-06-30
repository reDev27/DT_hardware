package Controller;

import Model.Carrello;
import Model.Cliente;
import Model.RequestUtility;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="BeforeCheckOutServ" , value = "/BeforeCheckOutServ")
public class BeforeCheckOutServ extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		if(RequestUtility.checkIsLogged(request.getSession()).compareTo("l") == 0)
		{
			Carrello carrello = (Carrello) request.getSession().getAttribute("carrello");
			ArrayList<String> notAvailableProducts;
			if(carrello!=null)
			{
				try
				{
					notAvailableProducts = carrello.verifyAvailability(request.getServletContext());
				}
				catch (SQLException throwables)
				{
					throwables.printStackTrace();
				}
				carrello.getTotale();
				int selectedAddress = Integer.parseInt(request.getParameter("selectedAddress"));
				int selectedCard = Integer.parseInt(request.getParameter("selectedCard"));
				HttpSession session = request.getSession();
				Cliente cliente = (Cliente) session.getAttribute("cliente");
				cliente.resizeArray(selectedAddress, selectedCard);
				RequestDispatcher dispatcher = request.getRequestDispatcher("riepilogoCheckout.jsp");
				dispatcher.forward(request, response);
			}
		}
		else
		{
			response.getWriter().write("error");
		}
	}
}