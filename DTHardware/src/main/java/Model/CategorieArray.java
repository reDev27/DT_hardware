package Model;

import java.util.ArrayList;

public class CategorieArray
{
	public CategorieArray()
	{

	}

	public CategorieArray(ArrayList<Categorie> categories)
	{
		this.categories=categories;
	}



	ArrayList<Categorie> categories;

public ArrayList<Categorie> getCategories()
{
	return categories;
}
public void setCategories(ArrayList<Categorie> categories)
{
	this.categories = categories;
}
}
