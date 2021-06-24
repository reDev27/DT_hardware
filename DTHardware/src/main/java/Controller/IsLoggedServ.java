package Controller;

import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "IsLoggedServ", value = "/IsLoggedServ")
public class IsLoggedServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		String isLogged= (String) request.getSession().getAttribute("isLogged");
		if(isLogged==null)
			isLogged=RequestUtility.checkIsLogged(request.getSession());
		PrintWriter writer=response.getWriter();
		if(isLogged.compareTo("n")==0)
			writer.write("{\"L\":false}");
		else
			writer.write("{\"L\":true}");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}