package Model;

public class Categoria
{
	public Categoria()
	{

	}
	public Categoria(String nome, int quantita)
	{
		this.nome = nome;
		this.quantita = quantita;
	}

	private String nome;
	private int quantita;

public String getNome()
{
	return nome;
}
public void setNome(String nome)
{
	this.nome = nome;
}
public int getQuantita()
{
	return quantita;
}
public void setQuantita(int quantita)
{
	this.quantita = quantita;
}
}
