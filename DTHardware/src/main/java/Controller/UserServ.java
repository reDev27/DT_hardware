package Controller;

import Model.DAO.UserBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

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
		String s=null;
		try
		{
			//AdminBean.callInsertProdotto("1", 5.89, "ciao", "asd", 6, "barilla", "geforce 2080 rigata");
			//Map<String, Object> ris=UserNotLoggedBean.callSelectProdotto("1");
			//Blob imm=UserBean.callSelectProdotto("1");
			//request.setAttribute("immagine", ris.get("immagineOut"));
			//CategorieArray cat=UserNotLoggedBean.callSelectCategoria();
			/*AdminBean.callInsertProdotto("0123",10,"mobo msi z570","chipset z570",5,"MSI","Z570 ACE");
			AdminBean.callInsertProdotto("0124",10,"mobo msi z590","chipset z590",5,"MSI","Z590 PRO");
			AdminBean.callInsertCategoria("Schede madri",10,0123);
			ProductsArray prodotti=new ProductsArray();
			UserNotLoggedBean.callSelectProdottoByCategoria("schedeMadri");*/
			s=UserBean.callSelectCarteDiCreditoByUsername("chicco").get(0).getnCarta();
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}

		//fileWrite(request.getServletPath());
		RequestDispatcher dispatcher=request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

	public static void fileWrite(String genericURL) throws IOException
	{
		File f=new File(genericURL + "/src/java/model/userPass");
		f.createNewFile();
		PrintWriter printWriter=new PrintWriter(f);
		printWriter.write("user, Tav0l1n0");
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	{

	}
}
