package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	
	
	public RequestSpecification requestSpecification(String BaseURI) throws IOException
	{
		if (reqSpec==null)
		{
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalProperty(BaseURI))
					.setContentType(ContentType.JSON)
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.addQueryParam("key", "qaclick123").build();
			return reqSpec;
		}
		
		return reqSpec;
	}
	
	public static String getGlobalProperty(String key) throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\resources\\globalData.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}
	
	/*
	 * public String getJsonPath(Response response, String Key) { String responseStr
	 * = response.asString(); JsonPath js = new JsonPath(responseStr);
	 * 
	 * return js.get(Key).toString(); }
	 */
	
	public String getJsonPath(Response response, String Key, String expValue)
	{
		String responseStr = response.asString();
		String value = null;
		Object json = new JSONTokener(responseStr).nextValue();
		if (json instanceof JSONArray)
		{
			System.out.println("Enter inside JsonArray");
			JSONArray ja = new JSONArray(responseStr);
			for(int i=0; i<ja.length(); i++)
			{
				JSONObject jo = ja.getJSONObject(i);
				if (jo.getString(Key).equalsIgnoreCase(expValue))
				{
					value = jo.getString(Key);
				}			
			}
		}
		else if (json instanceof JSONObject)
		{
			System.out.println("Enter inside JsonObject");
			JsonPath js = new JsonPath(responseStr);
			value = js.getString(Key);
		}
		return value;
	}

}
