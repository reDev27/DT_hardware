package Controller;

import Model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "testServ", value = "/testServ")
public class testServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Product product= (Product) request.getSession().getAttribute("product");
		response.getWriter().write(product.getCodiceABarre()+" "+product.getModello());
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}

}