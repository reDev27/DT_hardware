package Model;

import javax.servlet.http.HttpSession;

public class RequestUtility
{
	public static String checkIsLogged(HttpSession session)
	{
		String isLogged=(String) session.getAttribute("isLogged");
		if(isLogged == null)
			isLogged="n";
		session.setAttribute("isLogged", isLogged);
		return isLogged;
	}
}
