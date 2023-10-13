package utils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IAnnotationTransformer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import core.BaseTest;

import core.BasePage;

public class SuiteListener implements ITestListener, IAnnotationTransformer {

	@Override
	public void onTestFailure(ITestResult iTestResult) {
		String fileName = System.getProperty("user.dir") + File.separator + "screenshots" + File.separator
				+ iTestResult.getMethod().getMethodName();
		Object currentInstance =iTestResult.getInstance();
		File f = ((TakesScreenshot) ((BaseTest) currentInstance).getDriver()).getScreenshotAs(OutputType.FILE);

		try {
			FileUtils.copyFile(f, new File(fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void transform(ITestAnnotation iTestAnnotation, Class aClass, Constructor constructor, Method method) {
		iTestAnnotation.setRetryAnalyzer(RetryAnalyzer.class);
	}
}
