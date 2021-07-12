package Model.DAO;

import Model.*;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserBean extends UserNotLoggedBean
{
	public static void callDeleteAddressByVia(String via, int nCivico, String username, ServletContext context) throws IOException, SQLException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result = connection.deleteAddressByVia(via, nCivico, username, crd.getUsername(), crd.getPass());
	}

	public static ProductsOfAnOrder callSelectProductsByOrderId(int id, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result = connection.selectProductsByOrderId(id, crd.getUsername(), crd.getPass());
		ProductsOfAnOrder productsOfAnOrder=new ProductsOfAnOrder(id);
		ArrayList<Product> products = productsOfAnOrder.getProdotti();
		while (result.next())
		{
			products.add(
					new Product(
							result.getString("codicebarre"),
							result.getDouble("prezzo"),
							result.getString("immagine"),
							result.getInt("quantita"),
							result.getString("marca"),
							result.getString("modello")
					)
			);
		}
		return productsOfAnOrder;
	}

	public static int callSelectOrderByMaxId(String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result = connection.selectOrderByMaxId(username, crd.getUsername(), crd.getPass());
		result.next();
		return result.getInt("maxId");
	}

	public static ArrayList<Order> callSelectOrdersByUsername(String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result=connection.selectOrdersByUsername(username, crd.getUsername(), crd.getPass());
		ArrayList<Order> orders=new ArrayList<>();
		while (result.next())
		{
			int id=result.getInt("id");
			orders.add(
					new Order(
							id,
							result.getString("fattura"),
							result.getDouble("totale"),
							DateUtil.getCalendarFromStringWithSubstring(result.getString("dataacquisto")),
							username,
							callSelectProductsByOrderId(id, context)
					)
			);
		}
		return orders;
	}

	public static ArrayList<CreditCard> callSelectCarteDiCreditoByUsername(String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result=connection.selectCarteDiCreditoByUsername(username, crd.getUsername(), crd.getPass());
		ArrayList<CreditCard> creditCards=new ArrayList<>();
		while(result.next())
		{
			creditCards.add(new CreditCard(result.getString("ncarta"), DateUtil.getCalendarFromString(result.getString("scadenza")), result.getInt("CVV")));
		}
		connection.destroy();
		return creditCards;
	}

	public static ArrayList<Address> callSelectIndirizzoByUsername(String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result=connection.selectIndirizzoByUsername(username, crd.getUsername(), crd.getPass());
		ArrayList<Address> addresses=new ArrayList<>();
		while(result.next())
		{
			addresses.add(new Address(result.getString("via"), result.getInt("ncivico"), result.getString("citta"), result.getInt("cap"), result.getBoolean("flag")));
		}
		connection.destroy();
		return addresses;
	}

	public static Cliente callSelectClienteByUsername(String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		ResultSet result=connection.selectClienteByUsername(username, crd.getUsername(), crd.getPass());
		Cliente cliente=null;
		while(result.next())
		{
			cliente=new Cliente(result.getString("username"), result.getString("email"), result.getString("nome"), result.getString("cognome"), result.getString("ntelefono"), callSelectIndirizzoByUsername(username, context), callSelectCarteDiCreditoByUsername(username, context));
		}
		connection.destroy();
		return cliente;
	}

	public static boolean callInsertCompone(int nprodotti, String codiceABarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		try
		{
			ResultSet result=connection.insertCompone(nprodotti, codiceABarre, crd.getUsername(), crd.getPass());
			result.next();
			result.getString("codiceabarreIn");
		}
		catch (NullPointerException  e)
		{
			connection.destroy();
			return false;
		}
		connection.destroy();
		return true;
	}

	public static void callInsertOrdine(String fattura, double totale, Calendar dataAcquisto, String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		connection.insertOrdine(fattura, totale, dataAcquisto,username, crd.getUsername(), crd.getPass());
		connection.destroy();
	}
}
