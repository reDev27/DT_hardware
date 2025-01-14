package Controller;

import Model.Cliente;
import Model.DAO.UserBean;
import Model.Order;
import com.google.gson.Gson;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "ShowOrdini", value = "/ShowOrdini")
public class ShowOrdini extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session=request.getSession();
		ServletContext context=request.getServletContext();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			String username = (String) session.getAttribute("user");
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			ArrayList<Order> orders=null;
			if(cliente==null)
			{
				try
				{
					cliente=UserBean.callSelectClienteByUsername(username, context);
				}
				catch (SQLException throwables)
				{
					throwables.printStackTrace();
				}
			}
			try
			{
				if(cliente!=null)
					orders=cliente.getOrdersByUsername(username, context);
			}
			catch (SQLException | NullPointerException throwables)
			{
				throwables.printStackTrace();
			}
			session.setAttribute("cliente", cliente);
			session.setAttribute("orders", orders);
			Gson gson=new Gson();
			session.setAttribute("ordersJson", gson.toJson(orders));
			//session.setAttribute("clieteJson", gson.toJson(cliente));
			request.getRequestDispatcher("/showOrdini.jsp").forward(request, response);
		}
		else
		{
			response.sendRedirect("login.html");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}