package util;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class MyExtentReporter {
	
	public static ExtentReports getExtentReport() {
		
		String extentReportFilePath = System.getProperty("user.dir")+"\\reports\\extentreport.html";
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFilePath);
		sparkReporter.config().setReportName("DDFramework Test Practice Session Result");
		sparkReporter.config().setDocumentTitle("DDFramework Test Automation Result");
		
		ExtentReports extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
		extentReport.setSystemInfo("Selenium Version", "4.8.3");
		extentReport.setSystemInfo("Operating System", "Windows 11");
		extentReport.setSystemInfo("Executed By", "Amit Kumar");
		
		return extentReport;
		
	}

}
