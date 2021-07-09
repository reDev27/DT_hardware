package Controller;

import Model.DAO.AdminBean;
import Model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteProductServ", value = "/DeleteProductServ")
public class DeleteProductServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String) session.getAttribute("user")).compareTo("admin")==0)
		{
			String toEliminate=request.getParameter("toEliminate");
			AdminBean.callDeleteProduct(toEliminate, request.getServletContext());
		}
		else
			throw new Error();
	}
}