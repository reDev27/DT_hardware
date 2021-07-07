package Model.DAO;

import Model.CrdGiver;
import Model.Product;

import javax.servlet.ServletContext;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class AdminBean extends UserBean
{
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
		connection.insertProdotto(codiceABarre, prezzo, descrizione, specifiche, new ByteArrayInputStream(immagine.getBytes()), quantita, marca, modello,crd.getUsername(), crd.getPass(), nomeCategoria,dataInserimento);
		connection.destroy();
	}

	public static void callInsertProdotto(Product product, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context );
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.insertProdotto(product.getCodiceABarre(), product.getPrezzo(), product.getDescrizione(), product.getSpecifiche(), new ByteArrayInputStream(product.getImmagine().getBytes()), product.getQuantitaProdotto(), product.getMarca(), product.getModello(),crd.getUsername(), crd.getPass(), product.getNomeCategoria(), product.getDataInserimento());
		connection.destroy();
	}

	private static InputStream preparaImmagine(String path) throws FileNotFoundException
	{
		File file=new File(path);
		FileInputStream stream=new FileInputStream(file);
		return (InputStream) stream;
	}
}
