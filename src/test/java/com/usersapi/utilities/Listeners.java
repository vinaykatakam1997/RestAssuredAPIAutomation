package com.usersapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html"); 
		htmlReporter.config().setDocumentTitle("Automation report");
		htmlReporter.config().setReportName("Rest API testing report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "vinay");

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.PASS,"Test case Passed is "+result.getName()); 
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.FAIL,"TEST CASE FAILED IS"+result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.SKIP,"TEST CASE SKIPPED IS"+result.getName());
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}

