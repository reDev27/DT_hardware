package Controller;

import Model.DAO.AdminBean;
import Model.DAO.DateUtil;
import Model.Product;
import Model.ProductsArray;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;

@WebServlet(name="TestServ", value = "/TestServ")
public class TestServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		try
		{
			ProductsArray productsArray=new ProductsArray();
			productsArray.getProductsByCategoria("scheda madre", request.getServletContext());
			System.out.println(productsArray.getProdotti().get(2).getCodiceABarre());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	response.getWriter().write("TUTTO OK");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

		String img=request.getParameter("img");
		try
		{
			AdminBean.callInsertProdotto
					(
							"10",
							56.90,
							"ottimo prodotto",
							"balenottera mangia occhiali da vista",
							img,
							69,
							"Casio",
							"Piastrelle per pavimenti",
							"scheda madre",
							Calendar.getInstance(),
							request.getServletContext()
					);
		}
		catch (SQLException | IOException throwables)
		{
			throwables.printStackTrace();
		}
	}
}

