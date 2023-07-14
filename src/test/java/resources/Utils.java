package resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonArray;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utils {

	public static RequestSpecification reqSpec;
	public static HashMap<String, String> params = new HashMap<String, String>();

	public RequestSpecification requestSpecification() throws IOException {
		if (reqSpec == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logs.txt"));

			reqSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).setContentType(ContentType.JSON).build();

			return reqSpec;
		}
		return reqSpec;
	}

	public String getGlobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	public void generateQueryParameters(String key, String Value) throws IOException {
		params.put(key, Value);
	}

	public int getJsonIntValue(Response response, String key) throws IOException {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getInt(key);

	}

	public boolean getJsonBooleanValue(Response response, String key) throws IOException {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getBoolean(key);

	}

	public String getJsonStringValue(Response response, String key) throws IOException {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getString(key);

	}

	public double getJsonDoubleValue(Response response, String key) throws IOException {

		String resp = response.asString();
		JsonPath js = new JsonPath(resp);
		return js.getDouble(key);

	}

	public String getJsonStringValue(Response response, String key, int resultNumber) {

		String resp = response.asString();
		JsonPath jsonPath = new JsonPath(resp);

		// Extracting data from each result object
		String value = jsonPath.getString("results[" + (resultNumber - 1) + "]." + key + "");

		return value;
	}
}