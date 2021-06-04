package Model;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class gsonUtility
{
	public String getJsonFromThisObj(Object obj)
	{
		Gson converter=new Gson();
		return converter.toJson(obj);
	}

	public JsonObject getJsonFromExtSource(String toConvert)
	{
		Gson gson=new Gson();
		JsonObject obj=gson.fromJson(toConvert, JsonObject.class);
		return obj;
	}
}
