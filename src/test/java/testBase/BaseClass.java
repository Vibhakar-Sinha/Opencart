package testBase;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static WebDriver driver;
    public Logger logger;
    public Properties prop;
    @BeforeClass(groups={"Sanity","Regression","Master"})
    @Parameters({"os","browser"})
    public void setUp(String os,String br) throws IOException {

        //config.properties for common properties
        FileInputStream file=new FileInputStream("./src//test//resources//config.properties");
        prop=new Properties();
        prop.load(file);

        //Logger
        logger= LogManager.getLogger(this.getClass()); //Log4j - It will dynamically pick the class at run time

        if(prop.getProperty("execution_env").equalsIgnoreCase("remote")){
            DesiredCapabilities capabilities=new DesiredCapabilities();

            //Operating System
            switch(os.toLowerCase()){
                case "windows": capabilities.setPlatform(Platform.WIN10);break;
                case "mac": capabilities.setPlatform(Platform.MAC); break;
                case "linux": capabilities.setPlatform(Platform.LINUX); break;
                default:System.out.println("Invalid operation system");return;
            }
            //Browser
            switch(br.toLowerCase()){
                case "chrome": capabilities.setBrowserName("chrome");break;
                case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
                case "firefox": capabilities.setBrowserName("Firefox");break;
                default: System.out.println("Invalid browser");return;
            }

            driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        }
        if(prop.getProperty("execution_env").equalsIgnoreCase("local")){
            switch(br.toUpperCase()){
                case "CHROME": driver=new ChromeDriver(); break;
                case "FIREFOX": driver=new FirefoxDriver(); break;
                case "EDGE": driver=new EdgeDriver(); break;
                default:System.out.println("Browser not supported");return;
            }

        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //driver.get("https://www.automationexercise.com/");
        driver.get(prop.getProperty("appURL"));
    }

    @AfterClass(groups={"Sanity","Regression","Master"})
    public void tearDown(){
        driver.quit();
    }

    public String randomString(){
        String generatedString= RandomStringUtils.insecure().nextAlphabetic(5);
        return generatedString;
    }
    public String randomNumber(){
        String generatedNumber=RandomStringUtils.insecure().nextNumeric(10);
        return generatedNumber;
    }
    public String randomAlphaNumeric(){
        String generatedAlphaNumeric=RandomStringUtils.insecure().nextAlphanumeric(5);
        return generatedAlphaNumeric;
    }

    public String captureScreen(String tname) throws IOException{
        String timeStamp=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()); //Time stamp
        TakesScreenshot takesScreenshot=(TakesScreenshot)driver;
        File sourceFile=takesScreenshot.getScreenshotAs(OutputType.FILE);

        String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\"+tname+"_"+timeStamp+".png";
        File targetFile=new File(targetFilePath);
        sourceFile.renameTo(targetFile);

        return targetFilePath;

    }


}
