package Controller;

import Model.DAO.DateUtil;
import Model.DAO.UserNotLoggedBean;
import Model.RequestUtility;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

@WebServlet(name = "RegisterServ", value = "/RegisterServ")
public class RegisterServ extends HttpServlet
{
@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		boolean esito=true;
		String scadenza=request.getParameter("scadenza");
		if(scadenza.compareTo("")!=0)
			scadenza+= " 00:00:00";
		else
			scadenza=null;
		Integer cvv=null;
		String appoggio=request.getParameter("CVV");
		if(appoggio.compareTo("")!=0)
			cvv=Integer.parseInt(appoggio);
		if(RequestUtility.checkIsLogged(request.getSession()).compareTo("n")==0);
		{
			try
			{
				UserNotLoggedBean.callRegister
						(
								request.getServletContext(),
								request.getParameter("username"),
								request.getParameter("email"),
								request.getParameter("psw"),
								request.getParameter("nome"),
								request.getParameter("cognome"),
								request.getParameter("ntelefono"),
								request.getParameter("Ncarta"),
								DateUtil.getCalendarFromString(scadenza),
								cvv
						);
				UserNotLoggedBean.callInsertIndirizzo
						(
								request.getServletContext(),
								request.getParameter("AddVia"),
								Integer.parseInt(request.getParameter("AddCivico")),
								request.getParameter("AddCitta"),
								Integer.parseInt(request.getParameter("AddCap")),
								0,
								request.getParameter("username")
						);
			}
			catch (SQLException | NoSuchAlgorithmException | IOException e)
				{
					esito=false;
					e.printStackTrace();
				}
		}

		response.sendRedirect("homepage.html");
	}
}