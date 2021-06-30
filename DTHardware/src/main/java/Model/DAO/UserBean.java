package Model.DAO;

import Model.Address;
import Model.Cliente;
import Model.CrdGiver;
import Model.CreditCard;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class UserBean extends UserNotLoggedBean
{
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

	public static void callInsertCompone(int nprodotti, int id, String codiceABarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		connection.insertCompone(nprodotti, id, codiceABarre, crd.getUsername(), crd.getPass());
		connection.destroy();
	}

	public static  void callInsertOrdine(String fattura, double totale, Calendar dataAcquisto, String username, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(1);
		UserDAO connection=new UserDAO();
		connection.insertOrdine(fattura, totale,dataAcquisto,username, crd.getUsername(), crd.getPass());
		connection.destroy();
	}
}
