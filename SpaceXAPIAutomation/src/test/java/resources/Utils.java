package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class Utils {
	RequestSpecification request_spec;
	
	
	public RequestSpecification requestSpecification() throws IOException
	{
		PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
		request_spec = new RequestSpecBuilder()
				.setBaseUri(getGlobalValue("baseURI"))				
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))
				.setContentType(ContentType.JSON).build();
		return request_spec;
	}
	public static String getGlobalValue(String key) throws IOException
	{
		Properties p= new Properties();
		FileInputStream ip= new FileInputStream("src/test/java/resources/global.properties");
		p.load(ip);
		return p.getProperty(key);
		
	}

}
