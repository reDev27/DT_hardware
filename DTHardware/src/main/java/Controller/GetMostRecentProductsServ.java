package Controller;

import Model.ProductsArray;
import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="GetMostRecentProductsServ", value = "/GetMostRecentProductsServ")
public class GetMostRecentProductsServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		RequestUtility.checkIsLogged(request.getSession());
		ProductsArray productsArray=new ProductsArray();
		try
		{
			productsArray.getMostRecentProducts(request.getServletContext());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		String toSend=productsArray.getJsonFromThisObj(productsArray.getProdotti());
		writer.write(toSend);
	}
}