package listeners;

import helpers.CaptureHelpers;
import helpers.PropertiesHelper;
import utils.ExtentManager;
import utils.ExtentTestManager;
import logs.Log;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Log.info("onStart: " + iTestContext.getStartDate());
        PropertiesHelper.loadAllFiles();
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Log.info("onFinish: " + iTestContext.getEndDate());
        //Gửi mail

        //Kết thúc và thực thi Extents Report
        ExtentManager.getExtentReports().flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Log.info("Start test case " + iTestResult.getName());
        CaptureHelpers.startRecord(iTestResult.getName());

        //Bắt đầu ghi 1 TCs mới vào Extent Report
        ExtentTestManager.saveToReport(getTestName(iTestResult), getTestDescription(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Log.info("PASSED!! Test case " + iTestResult.getName());
        CaptureHelpers.stopRecord();

        //Extent Report
        ExtentTestManager.logMessage(Status.PASS, iTestResult.getName() + " is passed.");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Log.info("FAILED!! Test case " + iTestResult.getName());
        CaptureHelpers.stopRecord();
        //CaptureHelper.captureScreenshot(iTestResult.getName());

        Log.error(iTestResult.getThrowable());

        //Extent Report
        ExtentTestManager.addScreenshot(iTestResult.getName());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getThrowable().toString());
        ExtentTestManager.logMessage(Status.FAIL, iTestResult.getName() + " is failed.");
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        CaptureHelpers.stopRecord();

        //Extent Report
        ExtentTestManager.logMessage(Status.SKIP, iTestResult.getThrowable().toString());
    }
}