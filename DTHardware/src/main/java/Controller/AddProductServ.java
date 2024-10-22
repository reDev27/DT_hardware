package Controller;

import Model.DAO.AdminBean;
import Model.Product;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name = "AddProductServ", value = "/AddProductServ")
public class AddProductServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{

	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		HttpSession session=request.getSession();
		Product product=null;
		if(((String) session.getAttribute("user")).compareTo("admin")==0)
		{
			product=new Product
					(
							request.getParameter("codiceABarre"),
							request.getParameter("descrizione"),
							request.getParameter("specifiche"),
							Double.parseDouble(request.getParameter("prezzo")),
							request.getParameter("marca"),
							request.getParameter("modello"),
							request.getParameter("image"),
							Integer.parseInt(request.getParameter("quantita")),
							Calendar.getInstance(),
							request.getParameter("categoria")
					);
			try
			{
				AdminBean.callInsertProdotto(product, request.getServletContext());
			}
			catch (SQLException | IOException throwables)
			{
				throwables.printStackTrace();
			}
		}
		else
			throw new Error();
	}
}