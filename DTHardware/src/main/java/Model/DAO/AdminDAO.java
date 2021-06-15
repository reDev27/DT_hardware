package Model.DAO;

import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.Calendar;

public class AdminDAO extends UserDAO
{

	public void insertProdotto(String codiceaBarre, double prezzo, String descrizione, String specifiche, InputStream image, int quantita, String marca, String modello, String userType, String passData, String nomeCategoria, Calendar dataInserimento) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call InsertProdotto(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("codiceabarreIn", codiceaBarre);
		callableStatement.setString("prezzoIn", String.valueOf(prezzo));
		callableStatement.setString("descrizioneIn", descrizione);
		callableStatement.setString("specificheIn", specifiche);
		callableStatement.setBinaryStream("immagineIn", image);
		callableStatement.setString("quantitaIn", String.valueOf(quantita));
		callableStatement.setString("marcaIn", marca);
		callableStatement.setString("modelloIn", modello);
		callableStatement.setString("nomeIn", nomeCategoria);
		callableStatement.setString("datainserimentoIn", DateUtil.getStringFromCalendar(dataInserimento));
		doExecute(callableStatement);
	}

	public void insertCategoria(String nome, int quantita, int codiceaBarre,String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call InsertCategoria(?, ?, ?)}");
		callableStatement.setString("nomeIn", nome);
		callableStatement.setString("quantitaIn",String.valueOf(quantita));
		callableStatement.setString("codiceabarreIn",String.valueOf(codiceaBarre));
		doExecute(callableStatement);
	}
}
