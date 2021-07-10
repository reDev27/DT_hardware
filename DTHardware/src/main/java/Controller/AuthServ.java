package Controller;

import Model.Cliente;
import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
		request.getSession().removeAttribute("user");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		RequestUtility.checkIsLogged(session);
		Cliente user=new Cliente();
		try
		{
			if(user.authUser(request.getParameter("username"), request.getParameter("pass"), request.getServletContext(), request.getSession()))
			{
				session.setAttribute("user", request.getParameter("username"));
			}
		}
		catch (SQLException | NoSuchAlgorithmException | IOException throwables)
		{
			throwables.printStackTrace();
		}
		String username= (String) session.getAttribute("user");
		if(username!=null && username.compareTo("admin")==0)
			response.sendRedirect("manager.html");
		else
			response.sendRedirect("login.html?e");
	}
}