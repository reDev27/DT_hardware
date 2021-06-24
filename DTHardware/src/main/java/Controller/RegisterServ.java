package Controller;

import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterServ", value = "/RegisterServ")
public class RegisterServ extends HttpServlet
{
@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		if(RequestUtility.checkIsLogged(request.getSession()).compareTo("n")==0);
		{
			String parameter = request.getParameter("");
		}
		response.sendRedirect("homepage.html");
	}
}