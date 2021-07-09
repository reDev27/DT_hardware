package Controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteOrderServ", value = "/DeleteOrderServ")
public class DeleteOrderServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}