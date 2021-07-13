package Model;

public class StringUtility
{
	public static String subBlankNWithBR(String s)
	{
		return s.replace("\n", "<br>");
	}

	public static String subBlankNWithSpace(String s)
	{
		return s.replace("\n", " ");
	}

	public static String subVirgolette(String s)
	{
		return s.replace("\"", "&#34");
	}
}
