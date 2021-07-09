package Controller;

import Model.DAO.AdminBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

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
		HttpSession session=request.getSession();
		if(((String) session.getAttribute("user")).compareTo("admin")==0)
		{
			String toEliminate=request.getParameter("toEliminate");
			try
			{
				AdminBean.callDeleteOrder(Integer.parseInt(toEliminate), request.getServletContext());
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		else
			throw new Error();
	}
}