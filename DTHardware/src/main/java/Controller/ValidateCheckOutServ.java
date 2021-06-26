package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ValidateCheckOutServ", value = "/ValidateCheckOutServ")
public class ValidateCheckOutServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			String username = (String) session.getAttribute("username");

		}
		else
		{
			response.getWriter().write("error");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}