package Controller;

import Model.Ordine;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Locale;

public class AggiungiOrdineServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException
	{
		Gson gsonReader=new Gson();
		JsonObject jsonOrdine= gsonReader.fromJson((String) request.getAttribute("jsonOrdine"), JsonObject.class);
		Ordine ordine=new Ordine();
		ordine.ordineEffettuato(jsonOrdine);
		RequestDispatcher dispatcher=request.getRequestDispatcher("");
		dispatcher.forward(request, response);
	}
}