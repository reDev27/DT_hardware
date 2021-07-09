package Controller;

import Model.DAO.AdminBean;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GestioneOrdersRedirectServ", value = "/GestioneOrdersRedirectServ")
public class GestioneOrdersRedirectServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session=request.getSession();
		if(((String) session.getAttribute("user")).compareTo("admin")==0)
		{
			Gson gson=new Gson();
			try
			{
				request.setAttribute("ordersJson", gson.toJson(AdminBean.callSelectOrders(request.getServletContext())));
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			request.getRequestDispatcher("gestioneOrders.jsp").forward(request, response);
		}
		else
			throw new Error();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}

