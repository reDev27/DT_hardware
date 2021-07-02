package Model;

import javax.servlet.ServletContext;

public class ProductsOfAnOrder extends ProductsArray
{
	public ProductsOfAnOrder()
	{
		super();
	}
	public ProductsOfAnOrder(int id)
	{
		super();
		setId(id);
	}

	private int id;

	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
}
