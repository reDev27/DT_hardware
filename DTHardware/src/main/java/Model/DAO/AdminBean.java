package Model.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.SQLException;

public class AdminBean extends UserBean
{
	public static void callInsertCategoria(String nome, int quantita, int codiceaBarre) throws SQLException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
		connection.insertCategoria(nome, quantita, codiceaBarre, "root", "aaaa");
		connection.destroy();
	}

	public static void callInsertProdotto(String codiceABarre, double prezzo, String descrizione,String specifiche, int quantita, String marca, String modello) throws SQLException, FileNotFoundException
	{
		UserNotLoggedDAO connection=new UserNotLoggedDAO();
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
