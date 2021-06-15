package Controller;

import Model.DAO.AdminBean;
import Model.DAO.AdminDAO;
import Model.ProductsArray;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name="TestServ", value = "/TestServ")
public class TestServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
		{
			ProductsArray productsArray=new ProductsArray();
			productsArray.getProductsByCategoria("scheda madre", request.getServletContext());
			System.out.println(productsArray.getProdotti().get(2).getCodiceABarre());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	response.getWriter().write("TUTTO OK");
	}
}

