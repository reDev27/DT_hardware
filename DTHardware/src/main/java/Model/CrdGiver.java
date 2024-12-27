package Model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

public class CrdGiver
{

	public CrdGiver(ServletContext context) {
		this.context = context;
	}

	// Setter per modificare il percorso del file
	public void setCredentialsPath(String path) {
		this.credentialsPath = path;
	}

	/**
	 * Updates the credentials based on the user type.
	 *
	 * @param userType 0 for admin privileges, 1 for logged user privileges, 2 for not logged user privileges.
	 * @throws IOException if an error occurs while reading the credentials file
	 */
	public void aggiornaCrd(int userType) throws IOException {
		this.actualLog = userType;

		// Retrieve the resource as an InputStream
		InputStream inputStream = context.getResourceAsStream(this.credentialsPath);
		if (inputStream == null) {
			throw new IllegalStateException("Resource not found: " + this.credentialsPath);
		}

		// Read and parse the credentials JSON
		byte[] jsonFile = inputStream.readAllBytes();
		String toConvert = new String(jsonFile);
		Gson gson = new Gson();
		JsonObject crd = gson.fromJson(toConvert, JsonObject.class);

		// Navigate to the correct credentials based on user type
		crd = crd.get("credentials").getAsJsonObject();
		if (userType == 0) {
			crd = crd.getAsJsonObject("admin");
		} else if (userType == 1) {
			crd = crd.getAsJsonObject("user");
		} else {
			crd = crd.getAsJsonObject("userNotLogged");
		}

		// Extract username and password
		JsonElement user = crd.get("username");
		JsonElement pass = crd.get("password");
		this.username = user.getAsString();
		this.pass = pass.getAsString();
	}


	private final ServletContext context;
	private String credentialsPath = "/WEB-INF/crd.json"; // Default path
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
