package Controller;

import Model.Cliente;
import Model.DAO.UserBean;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "ValidateCheckOutServ", value = "/ValidateCheckOutServ")
public class ValidateCheckOutServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		HttpSession session=request.getSession();
		Cliente cliente=null;
		if(((String)session.getAttribute("isLogged")).compareTo("l")==0)
		{
			String username = (String) session.getAttribute("user");
			try
			{
				cliente= UserBean.callSelectClienteByUsername(username, request.getServletContext());
			}
			catch (SQLException throwables)
			{
				throwables.printStackTrace();
			}
			Gson gson=new Gson();
			response.getWriter().write(gson.toJson(cliente));
		}
		else
		{
			response.getWriter().write("error");
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}