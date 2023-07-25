package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseClass {

	public static RequestSpecification specrequest;
	ResponseSpecification specresponse;
	PrintStream log;

	public RequestSpecification requestspecification() throws IOException  {

		if (specrequest==null) {

			log = new PrintStream(new FileOutputStream("logging.txt"));

			specrequest = new RequestSpecBuilder().addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setBaseUri(propertyfile("BaseURL"))
					.setContentType(ContentType.JSON)
					.addQueryParam("key", "qaclick123").build();
		}
		return specrequest;

	}

	public ResponseSpecification responsespecification() {

		specresponse = new ResponseSpecBuilder()
				.expectContentType(ContentType.JSON)
				.expectStatusCode(200).build();
		return specresponse;
	}

	public static String propertyfile(String key) throws IOException {

		Properties property = new Properties();

		FileInputStream readfile = new FileInputStream("C:\\Users\\vigne\\eclipse-workspace\\Rest_Assured_Cucumber\\src\\test\\java\\resources\\Global.properties");

		property.load(readfile);

		String propertyfile = property.getProperty(key);
		return propertyfile;

	}

	public String getJsonPath(Response res,String key) {

		String responses = res.asString();
		JsonPath js = new JsonPath(responses);
		String jsonpath = js.get(key).toString();

		return jsonpath;
	}

}




