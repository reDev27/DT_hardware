package Controller;

import Model.Cliente;
import Model.DAO.AdminBean;
import Model.DAO.DateUtil;
import Model.DAO.UserNotLoggedBean;
import Model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "ModifyClienteServ", value = "/ModifyClienteServ")
public class ModifyClienteServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		Cliente cliente = null;
		if(((String) session.getAttribute("user")).compareTo("admin")==0)
		{
			try
			{
				AdminBean.callUpdateCliente
						(
								new Cliente
								(
									request.getParameter("username"),
									request.getParameter("email"),
									request.getParameter("nome"),
									request.getParameter("cognome"),
									request.getParameter("ntelefono")
								),
								request.getServletContext()
						);
			}
			catch (IOException | SQLException | NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
		else
			throw new Error();
	}
}