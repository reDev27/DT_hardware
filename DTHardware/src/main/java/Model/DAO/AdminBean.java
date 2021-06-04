package Model.DAO;

import Model.CrdGiver;

import javax.servlet.ServletContext;
import java.io.*;
import java.sql.SQLException;
import java.util.Calendar;

public class AdminBean extends UserBean
{
	public static void callInsertCategoria(String nome, int quantita, int codiceaBarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.insertCategoria(nome, quantita, codiceaBarre, crd.getUsername(), crd.getPass());
		connection.destroy();
	}

	public static void callInsertProdotto(String codiceABarre, double prezzo, String descrizione, String specifiche, int quantita, String marca, String modello, Calendar dataInserimento, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.insertProdotto(codiceABarre, prezzo, descrizione, specifiche, null/*preparaImmagine*/, quantita, marca, modello,crd.getUsername(), crd.getPass(), dataInserimento);
		connection.destroy();
	}

	private static InputStream preparaImmagine(String path) throws FileNotFoundException
	{
		File file=new File(path);
		FileInputStream stream=new FileInputStream(file);
		return (InputStream) stream;
	}
}
