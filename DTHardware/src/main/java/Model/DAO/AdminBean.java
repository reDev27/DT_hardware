package Model.DAO;

import Model.CrdGiver;

import javax.servlet.ServletContext;
import java.io.*;
import java.sql.SQLException;

public class AdminBean extends UserBean
{
	public static void callInsertCategoria(String nome, int quantita, int codiceaBarre, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.insertCategoria(nome, quantita, codiceaBarre, "root", "aaaa");
		connection.destroy();
	}

	public static void callInsertProdotto(String codiceABarre, double prezzo, String descrizione,String specifiche, int quantita, String marca, String modello, ServletContext context) throws SQLException, IOException
	{
		CrdGiver crd=new CrdGiver(context);
		crd.aggiornaCrd(0);
		AdminDAO connection=new AdminDAO();
		connection.insertProdotto(codiceABarre, prezzo, descrizione, specifiche, preparaImmagine("D:\\file_miei\\immagini\\disegno dove mostro le mie impareggiabli doti artistiche da disegnatore e grafico 4d iper ultra.png"),quantita, marca, modello,"root", "aaaa");
		connection.destroy();
	}

	private static InputStream preparaImmagine(String path) throws FileNotFoundException
	{
		File file=new File(path);
		FileInputStream stream=new FileInputStream(file);
		return (InputStream) stream;
	}
}
