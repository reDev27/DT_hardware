package Model;

import Model.DAO.UserNotLoggedBean;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoriesArray extends gsonUtility
{
	public CategoriesArray()
	{
		categories= new ArrayList<>();
	}
	public CategoriesArray(ArrayList<Category> categories)
	{
		this.categories=categories;
	}

	/////////

	public void initializeCategories(ServletContext context) throws SQLException, IOException
	{
		setCategories(UserNotLoggedBean.callSelectCategoria(context));
	}

	public void addCategoria(Category categoria)
	{
		categories.add(categoria);
	}

	public void addCategoria(String nome, int quantita)
	{
		Category categoria=new Category(nome, quantita);
		categories.add(categoria);
	}

	public Category getCategoria(int index)
	{
		return categories.get(index);
	}

	////////

	ArrayList<Category> categories;

	public ArrayList<Category> getCategories()
{
	return categories;
}
	public void setCategories(ArrayList<Category> categories)
{
	this.categories = categories;
}
}
