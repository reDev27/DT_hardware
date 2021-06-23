package Controller;

import Model.Cliente;
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
		String s= (String) request.getSession().getAttribute("isL");
		if(s.compareTo("a")==0 || s.compareTo("l")==0)
			response.getWriter().write("true");
		else
			response.getWriter().write("false");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		Cliente user=new Cliente();
		boolean isLogged=false;
		try
		{
			isLogged=user.authUser(request.getParameter("email"), request.getParameter("pass"), request.getServletContext(), request.getSession());
		}
		catch (SQLException | NoSuchAlgorithmException | IOException throwables)
		{
			throwables.printStackTrace();
		}
		if(isLogged)
		{
			response.sendRedirect("login.html?esito=true");
		}
		else
			response.sendRedirect("login.html?esito=false");
	}
}