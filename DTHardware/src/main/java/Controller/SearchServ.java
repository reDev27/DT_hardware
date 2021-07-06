package Controller;

import Model.DAO.UserNotLoggedBean;
import Model.Product;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "SearchServ", value = "/SearchServ")
public class SearchServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String toSearch=request.getParameter("keyToSearch");
		ArrayList<Product> products=null;
		try
		{
			products=UserNotLoggedBean.callSearchProducts(toSearch, request.getServletContext());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		Gson gson=new Gson();
		response.getWriter().write(gson.toJson(products));
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}