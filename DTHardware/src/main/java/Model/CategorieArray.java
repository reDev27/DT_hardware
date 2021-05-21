package Model;

import java.util.ArrayList;

public class CategorieArray
{
	public CategorieArray()
	{
		categories= new ArrayList<>();
	}
	public CategorieArray(ArrayList<Categoria> categories)
	{
		this.categories=categories;
	}

	/////////

	public void addCategoria(Categoria categoria)
	{
		categories.add(categoria);
	}

	public void addCategoria(String nome, int quantita)
	{
		Categoria categoria=new Categoria(nome, quantita);
		categories.add(categoria);
	}

	public Categoria getCategoria(int index)
	{
		return categories.get(index);
	}

	////////

	ArrayList<Categoria> categories;

public ArrayList<Categoria> getCategories()
{
	return categories;
}
public void setCategories(ArrayList<Categoria> categories)
{
	this.categories = categories;
}
}
