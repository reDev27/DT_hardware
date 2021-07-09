package Controller;

import Model.DAO.AdminBean;
import Model.DAO.DateUtil;
import Model.Order;
import Model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UpdateOrderServ", value = "/UpdateOrderServ")
public class UpdateOrderServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		Order order;
		if(((String) session.getAttribute("user")).compareTo("admin")==0)
		{
			order=new Order
					(
							Integer.parseInt(request.getParameter("id")),
							request.getParameter("fattura"),
							Double.parseDouble(request.getParameter("totale")),
							DateUtil.getCalendarFromStringWithSubstring(request.getParameter("dataAcquisto")),
							request.getParameter("username")
					);
			try
			{
				AdminBean.callUpdateOrder(order, request.getServletContext());
			}
			catch (SQLException | IOException throwables)
			{
				throwables.printStackTrace();
			}
		}
		else
			throw new Error();
	}
}