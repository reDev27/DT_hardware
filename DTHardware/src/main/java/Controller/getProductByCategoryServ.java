package Controller;

import Model.ProductsArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name="getProductByCategoryServ", value = "/getProductByCategoryServ")
public class getProductByCategoryServ extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ProductsArray productsArray=new ProductsArray();
		String category=request.getParameter("category");
		try
		{
			productsArray.getProductsByCategoria(category, request.getServletContext());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		String s=productsArray.getJsonFromThisObj(productsArray.getProdotti());
		writer.write(s);
	}
}