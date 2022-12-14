package com.usersapi.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.usersapi.base.TestBase;
import com.usersapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC004_Put_UpdateExistingUserData extends TestBase {

	String userName = RestUtils.userName();
	String userSalary = RestUtils.userSal();
	String userAge = RestUtils.userAge();

	@BeforeClass
	public void updateExistingUserData() throws InterruptedException {
		logger.info("*********Started TC004_Put_UpdateExistingUserData**********");

		RestAssured.baseURI = "https://reqres.in/api/users/2";
		httpRequest = RestAssured.given();

		// JSONObject is a class that represents a simple json. we can add key-value pairs
		// {"name"="john","salary"= 35000,"age"=28}
		JSONObject requestParams = new JSONObject();
		requestParams.put("name", userName);
		requestParams.put("age", userAge);
		requestParams.put("salary", userSalary);

		// Add a header stating the request body is a json
		httpRequest.header("Content-Type", "application/json");

		// add the json to the body of the request
		httpRequest.body(requestParams.toJSONString());

		response = httpRequest.request(Method.PUT, "/users/" + id);

		Thread.sleep(2000);
	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asPrettyString();
		logger.info("Response body ==>" + responseBody);
		Assert.assertEquals(responseBody.contains(userName), true);
		Assert.assertEquals(responseBody.contains(userAge), true);
		Assert.assertEquals(responseBody.contains(userSalary), true);
	}

	@Test
	public void checkStatusCode() {
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void checkContentType() {
		String contentType = response.header("Content-Type");
		Assert.assertEquals(contentType, "application/json; charset=utf-8");
	}

	@Test
	public void checkServerType() {
		String serverType = response.header("Server");
		Assert.assertEquals(serverType, "cloudflare");
	}

	@AfterClass
	public void tearDown() {
		logger.info("***********Finished TC004_Put_UpdateExistingUserData******** ");
	}
}
