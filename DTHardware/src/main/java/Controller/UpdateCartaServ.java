package Controller;

import Model.Cliente;
import Model.CreditCard;
import Model.DAO.DateUtil;
import Model.DAO.UserBean;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.rmi.ServerError;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

@WebServlet(name = "UpdateCartaServ", value = "/UpdateCartaServ")
public class UpdateCartaServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			CreditCard card = cliente.getCreditCards().get(0);
			try
			{
				UserBean.callInsertCartaDiCredito(request.getServletContext(), (String) session.getAttribute("user"), card.getnCarta(), card.getScadenza(), card.getCVV());
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			String scadenzaToParse = request.getParameter("scadenza");
			scadenzaToParse += "-01 00:00:00";
			Calendar scadenza = DateUtil.getCalendarFromString(scadenzaToParse);
			scadenza.set(Calendar.MONTH, scadenza.get(Calendar.MONTH)+1);
			CreditCard card = new CreditCard
					(
							request.getParameter("nCarta"),
							scadenza,
							Integer.parseInt(request.getParameter("CVV"))
					);
			ArrayList<CreditCard> cards = new ArrayList<>();
			cards.add(card);
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			cliente.setCreditCards(cards);
			session.setAttribute("cliente", cliente);
			String isToStore = request.getParameter("isToStore");
			if (isToStore.compareTo("true") == 0)
				doGet(request, response);
		}
		else
			throw new Error();
	}
}