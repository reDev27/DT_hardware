package Controller;

import Model.DAO.UserBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name="UserServ" , value = "/UserServ")
public class UserServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/*Calendar dataScadenza= Calendar.getInstance();
		dataScadenza.set(2024, Calendar.DECEMBER, 1, 7,39,46);
		Calendar dataAcquisto= Calendar.getInstance();
		boolean esitoLogin=false;
		try
		{
			UserBean.callRegister("chiccoPalmieri1", "chiccoPalmieri1@gmail.com", "aaaa", "Chicco", "Palmieri", "0", null, null, null);
			UserBean.callInsertCartaDiCredito("chiccoPalmieri1", "123456789012", dataScadenza, 678);
			//esitoLogin=UserBean.callLogin("admin00", "adn");
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}*/
		try
		{
			//UserBean.callInsertProdotto("1", 5.89, "ciao", "asd", 6, "barilla", "geforce 2080 rigata");
			UserBean.callSelectProdotto("1");
			//Blob imm=UserBean.callSelectProdotto("1");
			//request.setAttribute("immagine", imm);
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		UserBean.fileWrite(request.getServletPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}
