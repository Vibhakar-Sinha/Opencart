package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.HomePage;
import pageObjects.SignUpPage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {


    @Test(groups={"Sanity","Master"})
    public void verify_acc_registration(){
        try{
        HomePage hpobj=new HomePage(driver);

        logger.info("**** Starting TC001_AccountRegistrationTest **** ");
        hpobj.clickSignup();
        logger.info("Clicked on Login/Signup link");

        SignUpPage signupobj=new SignUpPage(driver);
        logger.info("Entering user name & email id for signup");
        //signupobj.setTxtname("John William");
        signupobj.setTxtname(randomString().toUpperCase());

        //signupobj.setEmail("john.william@gmail.com"); //We need to generate email id randomly
        signupobj.setEmail(randomAlphaNumeric()+"@gmail.com");

        signupobj.clickSignUp();

        logger.info("Entering user details for signup");
        String msgSignUp=signupobj.getSignUpMsg();
        logger.info("Validating message on Signup page");
        Assert.assertEquals(msgSignUp,"ENTER ACCOUNT INFORMATION");

        //signupobj.setRdoTitle("Mr");
        signupobj.setRdoTitle("Mrs");

        //signupobj.setTxtpassword("abcd1234");
        signupobj.setTxtpassword(randomAlphaNumeric());

        signupobj.setDrpDays("15");
        signupobj.setDrpMonths("July");
        signupobj.setDrpYears("2000");

        signupobj.setChkNewsLetter();
        signupobj.setChkOffers();

        //signupobj.setTxtFirstName("John");
        signupobj.setTxtFirstName(randomString().toUpperCase());
        //signupobj.setTxtlastName("Williams");
        signupobj.setTxtlastName(randomString().toUpperCase());

        signupobj.setTxtCompany("Accenture");
        signupobj.setTxtAddress1("Hinjewadi");
        signupobj.setTxtAddress2("Wakad");
        signupobj.setDrpCountry("Canada");
        signupobj.setTxtState("Maharashtra");
        signupobj.setTxtCity("Pune");
        signupobj.setTxtZipCode("412105");

        //signupobj.setTxtMobile("1234567890");
        signupobj.setTxtMobile(randomNumber());

        //signupobj.setBtnCreateAcc();
        //String msgAccountCreation=signupobj.setMsgConfirmation();
        //Assert.assertEquals(msgAccountCreation,"Account Created!");
        }
        catch(Exception e){
            logger.error("Test failed...");
            logger.debug("Debug logs...");
            Assert.fail();
        }

        logger.info("**** Finished TC001_AccountRegistrationTest **** ");
    }
}

