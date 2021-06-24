package Controller;

import Model.Cliente;
import Model.RequestUtility;
import com.google.gson.JsonObject;

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
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		RequestUtility.checkIsLogged(request.getSession());
		Cliente user=new Cliente();
		try
		{
			user.authUser(request.getParameter("email"), request.getParameter("pass"), request.getServletContext(), request.getSession());
		}
		catch (SQLException | NoSuchAlgorithmException | IOException throwables)
		{
			throwables.printStackTrace();
		}
		response.sendRedirect("login.html?e");
	}
}