package Controller;

import Model.Address;
import Model.Cliente;
import Model.CreditCard;
import Model.DAO.UserBean;
import Model.DAO.UserNotLoggedBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "UpdateIndirizzoServ", value = "/UpdateIndirizzoServ")
public class UpdateIndirizzoServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			Address address = cliente.getAddresses().get(0);
			try
			{
				UserBean.callInsertIndirizzo(request.getServletContext(), address.getVia(), address.getnCivico(), address.getCitta(), address.getCAP(), address.isActive()?1:0, (String) session.getAttribute("user"));
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
		}
		else
			throw new Error();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			Address address = new Address
					(
							request.getParameter("via"),
							Integer.parseInt(request.getParameter("nCivico")),
							request.getParameter("citta"),
							Integer.parseInt(request.getParameter("CAP")),
							true
					);
			ArrayList<Address> addresses = new ArrayList<>();
			addresses.add(address);
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			cliente.setAddresses(addresses);
			session.setAttribute("cliente", cliente);
			String isToStore = request.getParameter("isToStore");
			if (isToStore.compareTo("true") == 0)
				doGet(request, response);
		}
		else
			throw new Error();
	}
}