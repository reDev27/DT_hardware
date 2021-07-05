package Controller;

import Model.Address;
import Model.DAO.UserBean;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AddAddressServ", value = "/AddAddressServ")
public class AddAddressServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		ServletContext context=request.getServletContext();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			Address address = new Address
					(
							request.getParameter("via"),
							Integer.parseInt(request.getParameter("nCivico")),
							request.getParameter("citta"),
							Integer.parseInt(request.getParameter("CAP")),
							false
					);
			try
			{
				UserBean.callInsertIndirizzo(context, address.getVia(), address.getnCivico(), address.getCitta(), address.getCAP(), address.isActive()?1:0, (String) session.getAttribute("user"));
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
		}
		else
			response.sendRedirect("login.html");
	}
}