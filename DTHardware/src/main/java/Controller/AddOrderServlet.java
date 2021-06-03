package Controller;

import Model.Order;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AddOrderServlet extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Gson gsonReader=new Gson();
		JsonObject jsonOrdine= gsonReader.fromJson((String) request.getAttribute("jsonOrdine"), JsonObject.class);
		Order ordine=new Order();
		try
		{
			ordine.ordineEffettuato(jsonOrdine);
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		RequestDispatcher dispatcher=request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}
}