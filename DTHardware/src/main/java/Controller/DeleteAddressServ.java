package Controller;

import Model.DAO.UserBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "DeleteAddressServ", value = "/DeleteAddressServ")
public class DeleteAddressServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			String via=request.getParameter("via");
			int nCivico=Integer.parseInt(request.getParameter("nCivico"));
			try
			{
				UserBean.callDeleteAddressByVia(via, nCivico, (String) session.getAttribute("user"), request.getServletContext());
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
		}
		else
			response.sendRedirect("login.html");
	}
}