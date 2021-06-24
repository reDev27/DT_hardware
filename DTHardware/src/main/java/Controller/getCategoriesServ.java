package Controller;

import Model.CategoriesArray;
import Model.RequestUtility;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name="getCategoriesServ", value = "/getCategoriesServ")
public class getCategoriesServ extends HttpServlet
{
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		RequestUtility.checkIsLogged(request.getSession());
		CategoriesArray categorie=new CategoriesArray();
		try
		{
			categorie.initializeCategories(request.getServletContext());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter writer=response.getWriter();
		writer.write(categorie.getJsonFromThisObj(categorie.getCategories()));
	}
}
