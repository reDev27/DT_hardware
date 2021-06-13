package Model;

import Model.DAO.UserNotLoggedBean;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductsArray extends GsonUtility
{
	public ProductsArray()
	{
		prodotti=new ArrayList<>();
	}

	public void getProductsByCategoria(String category, ServletContext context) throws SQLException, IOException
	{
		prodotti=UserNotLoggedBean.callSelectProdottoByCategoria(category, context);
	}

	private ArrayList<Product> prodotti;

	public ArrayList<Product> getProdotti()
{
	return prodotti;
}
	public void setProdotti(ArrayList<Product> prodotti)
{
	this.prodotti = prodotti;
}
}

