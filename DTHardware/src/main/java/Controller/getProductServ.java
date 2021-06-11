package Controller;

import Model.ProductsArray;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;


@WebServlet(name="getProductServ", value = "/getProductServ")
public class getProductServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		ProductsArray productsArray=new ProductsArray();
		try
		{
			productsArray.getProductsByCategoria((String)request.getAttribute("category"), request.getServletContext());
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		try
		{
			productsArray.getProductsByCategoria("scheda madre", request.getServletContext());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		String s=productsArray.getJsonFromThisObj(productsArray.getProdotti());
		writer.write(s);

		/*RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);*/
	}
}