package Controller;

import Model.Carrello;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name="CheckOutServ" , value = "/CheckOutServ")
public class CheckOutServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Carrello carrello= (Carrello) request.getSession().getAttribute("carrello");
		ArrayList<String> notAvailableProducts;
		//verifica utente
		try
		{
			notAvailableProducts=carrello.verifyAvailability(request.getServletContext());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		carrello.getTotale();
		RequestDispatcher dispatcher=request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}
}