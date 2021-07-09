package Model.DAO;

import Model.CrdGiver;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class AdminDAO extends UserDAO
{
	public void deleteOrderById(int id, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteOrderById(?)}");
		callableStatement.setInt("idIn", id);
		doExecute(callableStatement);
	}

	public void updateOrder(int id, String fattura, double totale, Calendar dataAcquisto, String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call UpdateOrder(?, ?, ?, ?, ?)}");
		callableStatement.setInt("idIn", id);
		callableStatement.setString("fatturaIn", fattura);
		callableStatement.setDouble("totaleIn", totale);
		callableStatement.setString("dataacquistoIn", DateUtil.getStringFromCalendar(dataAcquisto));
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public ResultSet selectOrders(String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectOrders()}");
		doExecute(callableStatement);
		return getResult();
	}

	public void deleteClienteByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteClienteByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public void deleteComponeById(int id, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteComponeById(?)}");
		callableStatement.setInt("idIn", id);
		doExecute(callableStatement);
	}

	public void deleteOrdineByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteOrdineByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public void deleteRisiedeByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteRisiedeByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public void deleteCartaDiCreditoByUsername(String username, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteCartaDiCreditoByUsername(?)}");
		callableStatement.setString("usernameIn", username);
		doExecute(callableStatement);
	}

	public ResultSet selectProducts(String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectProducts()}");
		doExecute(callableStatement);
		return getResult();
	}

	public ResultSet selectClienti(String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement=user.prepareCall("{call SelectCliente()}");
		doExecute(callableStatement);
		return getResult();
	}

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

	public void updateProdotto(String codiceaBarre, double prezzo, String descrizione, String specifiche, InputStream image, int quantita, String marca, String modello, String userType, String passData, String nomeCategoria) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call UpdateProduct(?, ?, ?, ?, ?, ?, ?, ?, ?)}");
		callableStatement.setString("codiceabarreIn", codiceaBarre);
		callableStatement.setString("prezzoIn", String.valueOf(prezzo));
		callableStatement.setString("descrizioneIn", descrizione);
		callableStatement.setString("specificheIn", specifiche);
		callableStatement.setBinaryStream("immagineIn", image);
		callableStatement.setString("quantitaIn", String.valueOf(quantita));
		callableStatement.setString("marcaIn", marca);
		callableStatement.setString("modelloIn", modello);
		callableStatement.setString("nomeIn", nomeCategoria);
		doExecute(callableStatement);
	}

	public void updateCliente(String username, String eMail, String nome, String cognome, String nTelefono, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call UpdateCliente(?, ?, ?, ?, ?)}");
		callableStatement.setString("usernameIn", username);
		callableStatement.setString("emailIn", eMail);
		callableStatement.setString("nomeIn", nome);
		callableStatement.setString("cognomeIn", cognome);
		callableStatement.setString("ntelefonoIn", nTelefono);
		doExecute(callableStatement);
	}

	public void deleteProdotto(String codiceaBarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteProduct(?)}");
		callableStatement.setString("codiceabarreIn", codiceaBarre);
		doExecute(callableStatement);
	}

	public void deleteComponeByCodiceABarre(String codiceaBarre, String userType, String passData) throws SQLException
	{
		openConnection(userType, passData);
		CallableStatement callableStatement = user.prepareCall("{call DeleteComponeByCodiceABarre(?)}");
		callableStatement.setString("codiceabarreIn", codiceaBarre);
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
