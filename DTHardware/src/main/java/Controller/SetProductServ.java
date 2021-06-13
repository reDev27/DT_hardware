package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SetProductServ" , value = "/SetProductServ")
public class SetProductServ extends HttpServlet
{
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		request.getParameter("img");
		/*RequestDispatcher dispatcher=request.getRequestDispatcher("");
		dispatcher.forward(request, response);*/
	}
}
