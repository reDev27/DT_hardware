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

	public void getProductsByOrderId(String username, ServletContext context)
	{

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
