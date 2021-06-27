package Controller;

import Model.Cliente;
import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "AuthServ", value = "/AuthServ")
public class AuthServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		request.getSession().setAttribute("isLogged", "n");
		request.getSession().removeAttribute("username");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		RequestUtility.checkIsLogged(request.getSession());
		Cliente user=new Cliente();
		try
		{
			if(user.authUser(request.getParameter("email"), request.getParameter("pass"), request.getServletContext(), request.getSession()))
			{
				request.getSession().setAttribute("user", request.getParameter("email"));
			}
		}
		catch (SQLException | NoSuchAlgorithmException | IOException throwables)
		{
			throwables.printStackTrace();
		}
		response.sendRedirect("login.html?e");
	}
}