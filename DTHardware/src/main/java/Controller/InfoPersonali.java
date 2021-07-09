package Controller;

import Model.Cliente;
import Model.DAO.UserBean;
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

@WebServlet(name = "InfoPersonali", value = "/InfoPersonali")
public class InfoPersonali extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session=request.getSession();
		ServletContext context=request.getServletContext();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			Cliente cliente=null;
			try
			{
				cliente= UserBean.callSelectClienteByUsername((String) session.getAttribute("user"), context);
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			if(cliente!=null)
			{
				try
				{
					cliente.setCreditCards(UserBean.callSelectCarteDiCreditoByUsername((String) session.getAttribute("user"), context));
				}
				catch (SQLException throwables)
				{
					throwables.printStackTrace();
				}
			}
			if(cliente!=null)
			{
				try
				{
					cliente.setAddresses(UserBean.callSelectIndirizzoByUsername((String) session.getAttribute("user"), context));
				}
				catch (SQLException throwables)
				{
					throwables.printStackTrace();
				}
			}
			Gson gson=new Gson();
			session.setAttribute("clienteJson", gson.toJson(cliente));
			request.getRequestDispatcher("/infoPersonali.jsp").forward(request, response);
		}
		else
			response.sendRedirect("login.html");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}