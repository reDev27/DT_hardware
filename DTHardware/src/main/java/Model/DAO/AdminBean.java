package Model.DAO;

import Model.Cliente;
import Model.CrdGiver;
import Model.Order;
import Model.Product;

import javax.servlet.ServletContext;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminBean extends UserBean
{
	public static void callDeleteOrder(int id, ServletContext context) throws IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		try
		{
			connection.deleteComponeById(id, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		try
		{
			connection.deleteOrderById(id, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	}

	public static void callUpdateOrder(Order order,ServletContext context) throws IOException, SQLException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.updateOrder(order.getId(), order.getFattura(), order.getTotale(), order.getDataAcquisto(), order.getUsername(), crd.getUsername(), crd.getPass());
	}

	public static ArrayList<Order> callSelectOrders(ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ResultSet result=connection.selectOrders(crd.getUsername(), crd.getPass());
		ArrayList<Order> orders=new ArrayList<>();
		while(result.next())
		{
			orders.add(new Order
					(
							result.getInt("id"),
							result.getString("fattura"),
							result.getDouble("totale"),
							DateUtil.getCalendarFromStringWithSubstring(result.getString("dataacquisto")),
							result.getString("username")
					));
		}
		connection.destroy();
		return orders;
	}

	public static void callDeleteCliente(String username, ServletContext context) throws IOException, SQLException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ArrayList<Order> orders=callSelectOrdersByUsername(username, context);
		for(Order order:orders)
		{
			connection.deleteComponeById(order.getId(), crd.getUsername(), crd.getPass());
		}
		try
		{
			connection.deleteCartaDiCreditoByUsername(username, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		try
		{
			connection.deleteRisiedeByUsername(username, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		try
		{
			connection.deleteOrdineByUsername(username, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		try
		{
			connection.deleteClienteByUsername(username, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	}

	public static void callDeleteProduct(String codiceABarre, ServletContext context) throws IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		try
		{
			connection.deleteComponeByCodiceABarre(codiceABarre, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
		try
		{
			connection.deleteProdotto(codiceABarre, crd.getUsername(), crd.getPass());
		}
		catch (SQLException throwables)
		{
			throwables.printStackTrace();
		}
	}

	public static void callUpdateProduct(Product product, ServletContext context) throws IOException, SQLException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ByteArrayInputStream outputStream=null;
		if(product.getImmagine()!=null)
			outputStream=new ByteArrayInputStream(product.getImmagine().getBytes());
		connection.updateProdotto(	product.getCodiceABarre(), product.getPrezzo(), product.getDescrizione(), product.getSpecifiche(),
									outputStream, product.getQuantitaProdotto(), product.getMarca(),
									product.getModello(), crd.getUsername(), crd.getPass(), product.getNomeCategoria());
	}

	public static void callUpdateCliente(Cliente cliente,ServletContext context) throws IOException, SQLException, NoSuchAlgorithmException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.updateCliente(cliente.getUsername(), cliente.getEmail(), cliente.getNome(),
								cliente.getCognome(), cliente.getnTelefono(), crd.getUsername(), crd.getPass());
	}

	public static ArrayList<Cliente> callSelectClienti(ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ResultSet result=connection.selectClienti(crd.getUsername(), crd.getPass());
		ArrayList<Cliente> clienti=new ArrayList<>();
		while(result.next())
		{
			clienti.add(new Cliente
					(
							result.getString("username"),
							result.getString("email"),
							result.getString("nome"),
							result.getString("cognome"),
							result.getString("ntelefono")
					));
		}
		connection.destroy();
		return clienti;
	}

	public static ArrayList<Product> callSelectProducts(ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ResultSet result=connection.selectProducts(crd.getUsername(), crd.getPass());
		ArrayList<Product> products=new ArrayList<>();
		while(result.next())
		{
			products.add(new Product
			(
					result.getString("codicebarre"),
					result.getString("descrizione"),
					result.getString("specifiche"),
					result.getDouble("prezzo"),
					result.getString("marca"),
					result.getString("modello"),
					result.getString("immagine"),
					result.getInt("quantita"),
					DateUtil.getCalendarFromStringWithSubstring(result.getString("dataInserimento")),
					result.getString("nome")

			));
		}
		connection.destroy();
		return products;
	}

	public static void callInsertCategoria(String nome, int quantita, int codiceaBarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.insertCategoria(nome, quantita, codiceaBarre, crd.getUsername(), crd.getPass());
		connection.destroy();
	}

	public static void callInsertProdotto(String codiceABarre, double prezzo, String descrizione, String specifiche, String immagine,int quantita, String marca, String modello, String nomeCategoria,Calendar dataInserimento, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ByteArrayInputStream outputStream=null;
		if(immagine!=null)
			outputStream=new ByteArrayInputStream(immagine.getBytes());
		connection.insertProdotto(codiceABarre, prezzo, descrizione, specifiche, outputStream, quantita, marca, modello,crd.getUsername(), crd.getPass(), nomeCategoria,dataInserimento);
		connection.destroy();
	}

	public static void callInsertProdotto(Product product, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		ByteArrayInputStream outputStream=null;
		if(product.getImmagine()!=null)
			outputStream=new ByteArrayInputStream(product.getImmagine().getBytes());
		connection.insertProdotto(product.getCodiceABarre(), product.getPrezzo(), product.getDescrizione(), product.getSpecifiche(), outputStream, product.getQuantitaProdotto(), product.getMarca(), product.getModello(),crd.getUsername(), crd.getPass(), product.getNomeCategoria(), product.getDataInserimento());
		connection.destroy();
	}

	private static InputStream preparaImmagine(String path) throws FileNotFoundException
	{
		File file=new File(path);
		FileInputStream stream=new FileInputStream(file);
		return (InputStream) stream;
	}
}
