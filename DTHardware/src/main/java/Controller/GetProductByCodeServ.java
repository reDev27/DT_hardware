package Controller;

import Model.DAO.UserNotLoggedBean;
import Model.Product;
import Model.RequestUtility;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "GetProductByCodeServ", value = "/GetProductByCodeServ")
public class GetProductByCodeServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		RequestUtility.checkIsLogged(request.getSession());
		String codiceABarre=request.getParameter("codiceABarre");
		Product product=null;
		try
		{
			product= UserNotLoggedBean.callSelectProdottoByCodiceABarre(codiceABarre, request.getServletContext());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		request.setAttribute("product", product);
		request.getRequestDispatcher("viewProduct.jsp").forward(request,response);
	}

}