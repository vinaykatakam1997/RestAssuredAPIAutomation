package com.usersapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.usersapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_SingleUser extends TestBase {
	
	@BeforeClass
	public void getSingleUser() {
		logger.info("***********Started TC002_Get_SingleUser******** ");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/users/"+id);
	}

	@Test
	public void checkResponseBody() {
		logger.info("***********checkResponseBody******** ");
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response body ==>" + responseBody);
		Assert.assertTrue(responseBody != null);
	}

	@Test
	public void checkStatusCode() {
		
		int statusCode = response.getStatusCode();
		logger.info("Status code is =" + statusCode);
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkResponseTime() {
		
		long responseTime = response.getTime();
		logger.info("Response time is =" + responseTime);
		if (responseTime > 2000)
			logger.warn("Response time is greater than 2000");
		Assert.assertTrue(responseTime < 2000);
	}
	
	@Test
	public void checkContentType() {
		logger.info("***********checkContentType******** ");
		String contentType = response.header("Content-Type");
		logger.info("Content type is =" + contentType);
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	public void checkServerType() {
		logger.info("***********checkServerType******** ");
		String serverType = response.header("Server");
		logger.info("Server type is =" + serverType);
		Assert.assertEquals(serverType, "cloudflare");
	}

	@Test
	public void checkContentEncoding() {
		logger.info("***********checkContentEncoding******** ");
		String ContentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is =" + ContentEncoding);
		Assert.assertEquals(ContentEncoding, "gzip");
	}
	
	@AfterClass
	public void tearDown() {
		logger.info("***********Finished TC002_Get_SingleUser******** ");
	}
}

