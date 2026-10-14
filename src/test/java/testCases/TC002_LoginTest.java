package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC002_LoginTest extends BaseClass {

    @Test(groups={"Regression","Master"})
    public void verify_login(){

        logger.info("**** Starting TC002_LoginTest ****");

        try{
            HomePage hp=new HomePage(driver);
            hp.clickSignup();

            LoginPage lp=new LoginPage(driver);
            lp.setTxtLoginEmail(prop.getProperty("email"));
            lp.setTxtLoginPassword(prop.getProperty("password"));
            lp.clickLogin();

            MyAccountPage myacc=new MyAccountPage(driver);
            boolean targetPage=myacc.isLogoutExist();

            Assert.assertTrue(targetPage);

        }
        catch (Exception e) {
            Assert.fail();
        }
        logger.info("**** Finished TC002_LoginTest ****");
    }
}
