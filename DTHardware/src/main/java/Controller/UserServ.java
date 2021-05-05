package Controller;

import Model.UserBean;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name="UserServ" , value = "/UserServ")
public class UserServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserBean userUtil=new UserBean();
		String nome=request.getParameter("nome");
		String password=request.getParameter("password");
		if(nome!=null && password!=null)
		{
			try
			{
				userUtil.register(nome, password);
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

		RequestDispatcher dispatcher=request.getRequestDispatcher("LoginForm.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		UserBean userUtil=new UserBean();
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
		dispatcher.forward(request, response);
	}
}
