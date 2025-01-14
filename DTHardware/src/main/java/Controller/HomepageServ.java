package Controller;

import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GeneraleServ", value = "/GeneraleServ")
public class HomepageServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		//init servlet
		RequestUtility.checkIsLogged(request.getSession());
		response.sendRedirect("homepage.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}

}