package utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import testBase.BaseClass;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExtentReportManager implements ITestListener {

    public ExtentSparkReporter sparkReporter;
    public ExtentReports extent;
    public ExtentTest test;

    String repName;

    public void onStart(ITestContext testContext){

        /*
        SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Date dt=new Date();
        String currentDateTimeStamp=df.format(dt);
         */

        String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //Time stamp
        repName="Test-Report-"+timeStamp+".html";

        sparkReporter=new ExtentSparkReporter(".\\reports\\"+timeStamp); //Specify location of the report

        sparkReporter.config().setDocumentTitle("Opencart Automation Report"); //Title of the report
        sparkReporter.config().setReportName("Opencart Functional Testing"); //Name of the report
        sparkReporter.config().setTheme(Theme.DARK);

        extent=new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Application","Opencart");
        extent.setSystemInfo("Module","Admin");
        extent.setSystemInfo("Sub Module","Customers");
        extent.setSystemInfo("User name",System.getProperty("user.name"));
        extent.setSystemInfo("Environment","QA");

        String os=testContext.getCurrentXmlTest().getParameter("os"); //Getting os name dynamically from xml file
        extent.setSystemInfo("Operating System",os);

        String browser=testContext.getCurrentXmlTest().getParameter("browser"); //Getting browser name dynamically from xml file
        extent.setSystemInfo("Browser",browser);

        List<String> includedGroups=testContext.getCurrentXmlTest().getIncludedGroups();
        if(!includedGroups.isEmpty()){
            extent.setSystemInfo("Groups",includedGroups.toString());
        }
    }

    public void onTestSuccess(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); //To display groups in report

        test.log(Status.PASS,result.getName()+" got successfully executed");
    }

    public void onTestFailure(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); //To display groups in report

        test.log(Status.FAIL,result.getName()+" got failed");
        test.log(Status.INFO, result.getThrowable().getMessage());

        //Screenshot
        try{
            String imgPath=new BaseClass().captureScreen(result.getName());
            test.addScreenCaptureFromPath(imgPath);
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    public void onTestSkipped(ITestResult result){
        test= extent.createTest(result.getTestClass().getName());
        test.assignCategory(result.getMethod().getGroups()); //To display groups in report

        test.log(Status.SKIP,result.getName()+" got skipped");
        test.log(Status.INFO, result.getThrowable().getMessage());

    }
    public void onFinish(ITestContext testContext){
        extent.flush();

        /*

        //To open report automatically in browser after execution
        String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
        File extentReport=new File(pathOfExtentReport);

        try{
            Desktop.getDesktop().browse(extentReport.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
        */

        /*
        //For sending email
        try{
            URL url=new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+repName);

            //Create email message
            ImageHtmlEmail email=new ImageHtmlEmail();
            email.setDataSourceResolver(new DataSourceUrlResolver(url));
            email.setHostName("smtp.googlemail.com");
            email.setAuthenticator(new DefaultAuthenticator("vibhakar.sinha.ran@gmail.com","password"));
            email.setSSLOnConnect(true);
            email.setFrom("vibhakar.sinha.ran@gmail.com"); //Sender
            email.setSubject("Test Results");
            email.setMsg("Please find attached report...");
            email.addTo("vibhakar.sinha12@gmail.com");
            email.attach(url,"extent report","Please check report....");
            email.send(); //Send the email
        }
        catch (Exception e){
            e.printStackTrace();
        }
         */


    }
}
