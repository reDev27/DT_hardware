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
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name="UserServ" , value = "/UserServ")
public class UserServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Calendar dataScadenza= Calendar.getInstance();
		dataScadenza.set(2024, Calendar.DECEMBER, 1, 0,0,0);
		Calendar dataAcquisto= Calendar.getInstance();
		boolean esitoLogin=false;
		try
		{
			//UserBean.callRegister("chiccoPalmieri1", "chiccoPalmieri1@gmail.com", "aaaa", "Chicco", "Palmieri", "0", dataAcquisto, "0", dataScadenza, 999);
			esitoLogin=UserBean.callLogin("admin00", "admin");
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}

		System.out.println(esitoLogin);
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		/*UserBean userUtil=new UserBean();
		String nome=request.getParameter("nome");
		String password=request.getParameter("password");
		String nomeLogged=null;
		if(nome!=null && password!=null)
		{
			try
			{
				nomeLogged=userUtil.login(nome, password);
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			catch (NoSuchAlgorithmException e)
			{
				e.printStackTrace();
			}
		}
		boolean esito=false;
		if(nomeLogged!=null)
		{
			request.setAttribute("nome", nomeLogged);
			esito=true;
		}
		request.setAttribute("esito", esito);
		RequestDispatcher dispatcher=request.getRequestDispatcher("resultOfLogin.jsp");
		dispatcher.forward(request, response);*/
	}
}
