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
import java.util.Date;

@WebServlet(name="UserServ" , value = "/UserServ")
public class UserServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Calendar dataScadenza= Calendar.getInstance();//new Date()
		dataScadenza.set(Calendar.YEAR, 2024);
		dataScadenza.set(Calendar.MONTH, Calendar.JUNE);
		System.out.println(dataScadenza.getTime());
		String s= dataScadenza.get(Calendar.YEAR)+"-"+dataScadenza.get(Calendar.DAY_OF_MONTH);


		try
		{
			UserBean.register("chiccoPalmieri1", "chiccoPalmieri1@gmail.com", "aaaa", "Chicco", "Palmieri", "0", Calendar.getInstance(), 0, dataScadenza, 999);
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		try
		{
			System.out.println(UserBean.login("admin00", "admin"));
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
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
