package testCases;
/*
Data is valid - login successful - Pass - Logout
Data is valid - login unsuccessful - Fail

Data is invalid - login successful - Fail - Logout
Data is invalid - login unsuccessful - Pass

Data
 */

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.Dataproviders;

public class TC003_LoginDDT extends BaseClass {

    @Test(dataProvider="LoginData",dataProviderClass= Dataproviders.class,groups="DataDriven") //Getting data provider from different class
    public void verify_loginDDT(String email,String pwd,String exp){

        try{
            logger.info("***** Starting TC_003_LoginDDT ****");
            HomePage hp=new HomePage(driver);
            hp.clickSignup();

            LoginPage lp=new LoginPage(driver);
            lp.setTxtLoginEmail(email);
            lp.setTxtLoginPassword(pwd);
            lp.clickLogin();

            MyAccountPage myacc=new MyAccountPage(driver);
            boolean targetPage=myacc.isLogoutExist();

            if(exp.equalsIgnoreCase("Valid")){
                if(targetPage){
                    myacc.clickLogout();
                    Assert.assertTrue(true);
                }
                else{
                    Assert.fail();
                }
            }

            if(exp.equalsIgnoreCase("Invalid")){
                if(targetPage){
                    myacc.clickLogout();
                    Assert.fail();
                }
                else{
                    Assert.assertTrue(true);
                }
            }
            logger.info("***** Finished TC_003_LoginDDT ****");
        }
        catch (Exception e){
            Assert.fail();
        }
    }

}
