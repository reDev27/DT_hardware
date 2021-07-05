package Controller;

import Model.Address;
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
import java.util.ArrayList;

@WebServlet(name = "ShowChangeIndirizzi", value = "/ShowChangeIndirizzi")
public class ShowChangeIndirizzi extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		HttpSession session=request.getSession();
		ServletContext context=request.getServletContext();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			ArrayList<Address> addresses=null;
				try
				{
					addresses = UserBean.callSelectIndirizzoByUsername((String) session.getAttribute("user"), context);
				}
				catch (SQLException throwables)
				{
					throwables.printStackTrace();
				}
			Gson gson=new Gson();
			session.setAttribute("addressesJson", gson.toJson(addresses));
			request.getRequestDispatcher("/showChangeAddresses.jsp").forward(request, response);
		}
		else
			throw new Error();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}