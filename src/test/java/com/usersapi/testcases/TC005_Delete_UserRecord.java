package com.usersapi.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.usersapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class TC005_Delete_UserRecord extends TestBase {

	@BeforeClass
	public void deleteUserRecord() {
		logger.info("*********Started TC005_Delete_UserRecord**********");
		RestAssured.baseURI = "https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/users?page=2");

		// First get the JsonPath object instance from the response interface
		JsonPath jsonPathEvaluator = response.jsonPath();

		// Capture id
		String userID = jsonPathEvaluator.get("[0].id");
		 response = httpRequest.request(Method.DELETE, "/users/" + userID); // Pass ID
		// to delete record
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
		Assert.assertEquals(statusCode, 204);
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
		Assert.assertEquals(contentType, null);
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
		Assert.assertEquals(ContentEncoding, null);
	}

	@AfterClass
	public void tearDown() {
		logger.info("***********Finished TC005_Delete_UserRecord******** ");
	}

}
