package Controller;

import Model.DAO.UserNotLoggedBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ValidateValuesServ", value = "/ValidateValuesServ")
public class ValidateValuesServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		boolean esito=false;
		try
		{
			esito=UserNotLoggedBean.callSelectUsername(request.getParameter("username"), request.getServletContext());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		if(esito)
			request.getRequestDispatcher("RegisterServ").forward(request, response);
		else
			response.getWriter().write("errore");
	}
}