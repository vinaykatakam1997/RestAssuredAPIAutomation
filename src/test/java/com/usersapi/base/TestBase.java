package com.usersapi.base;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TestBase {
	
	public static RequestSpecification httpRequest;
	public static Response response;	
	public Logger logger;
	
	public String id="2";

	@BeforeClass
	public void setup() {
		logger= Logger.getLogger("UsersRestAPI"); // Added logger
		PropertyConfigurator.configure("Log4j.properties"); // Added logger
		logger.setLevel(Level.DEBUG);		
	}
}
