package Model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
import java.io.IOException;

public class CrdGiver
{
	public CrdGiver(ServletContext context)
	{
		this.context=context;
	}

	/**
	 @param userType : 0 is for the admin privileges; 1 is for the logged user privileges; 2 is for the NOT logged user privileges.
	 */
	public void aggiornaCrd(int userType) throws IOException
	{
		if(actualLog==userType)
			return;
		this.actualLog=userType;
		byte[] jsonFile= context.getResourceAsStream("/WEB-INF/crd.json").readAllBytes();
		String toConvert=new String(jsonFile);
		Gson gson=new Gson();
		JsonObject crd=gson.fromJson(toConvert, JsonObject.class);
		crd=crd.get("credentials").getAsJsonObject();
		if(userType==0)
			crd=crd.getAsJsonObject("admin");
		else if(userType==1)
			crd=crd.getAsJsonObject("user");
		else
			crd=crd.getAsJsonObject("userNotLogged");
		JsonElement user=crd.get("username");
		JsonElement pass=crd.get("password");
		this.username=user.getAsString();
		this.pass=pass.getAsString();
	}

	private ServletContext context;
	private int actualLog;
	private String username;
	private String pass;

	public int getActualLog()
	{
		return actualLog;
	}
	public void setActualLog(int actualLog)
	{
		this.actualLog = actualLog;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getPass()
	{
		return pass;
	}
	public void setPass(String pass)
	{
		this.pass = pass;
	}
}
