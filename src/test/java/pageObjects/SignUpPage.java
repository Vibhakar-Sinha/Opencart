package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

    //Constructors
    public SignUpPage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(xpath="//input[@placeholder='Name']")
    WebElement txtname;

    @FindBy(xpath="//input[@data-qa='signup-email']")
    WebElement txtemail;

    @FindBy(xpath="//button[normalize-space()='Signup']")
    WebElement btbSignUp;

    @FindBy(id="id_gender1")
    WebElement rdoTitleMale;

    @FindBy(id="id_gender2")
    WebElement rdoTitleFemale;

    @FindBy(id="password")
    WebElement txtpassword;

    @FindBy(id="days")
    WebElement drpDays;

    @FindBy(id="months")
    WebElement drpMonths;

    @FindBy(id="years")
    WebElement drpYears;

    @FindBy(id="newsletter")
    WebElement chkNewsLetter;

    @FindBy(id="optin")
    WebElement chkOffers;

    @FindBy(id="first_name")
    WebElement txtFirstName;

    @FindBy(id="last_name")
    WebElement txtlastName;

    @FindBy(id="company")
    WebElement txtCompany;

    @FindBy(id="address1")
    WebElement txtAddress1;

    @FindBy(id="address2")
    WebElement txtAddress2;

    @FindBy(id="country")
    WebElement drpCountry;

    @FindBy(id="state")
    WebElement txtState;

    @FindBy(id="city")
    WebElement txtCity;

    @FindBy(id="zipcode")
    WebElement txtZipCode;

    @FindBy(id="mobile_number")
    WebElement txtMobile;

    @FindBy(xpath="//button[normalize-space()='Create Account']")
    WebElement btnCreateAcc;

    @FindBy(xpath="//b[normalize-space()='Enter Account Information']")
    WebElement msgSignUp;

    @FindBy(xpath="//b[normalize-space()='Account Created!']")
    WebElement msgConfirmation;

    //Action Methods
    public void setTxtname(String fullname){
        txtname.sendKeys(fullname);
    }

    public void setEmail(String email){
        txtemail.sendKeys(email);
    }

    public void clickSignUp(){
        btbSignUp.click();
    }
    public String getSignUpMsg(){
        try{
            return msgSignUp.getText();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

    public void setRdoTitle(String title){
        rdoTitleMale.click();
        if(title.equals("Mrs")){
            rdoTitleFemale.click();
        }
    }

    public void setTxtpassword(String pwd){
        txtpassword.sendKeys(pwd);
    }

    public void setDrpDays(String days){
        selectDropdown(drpDays,days);
    }
    public void setDrpMonths(String months){
        selectDropdown(drpMonths,months);
    }
    public void setDrpYears(String years){
        selectDropdown(drpYears,years);
    }

    public void setChkNewsLetter(){
        chkNewsLetter.click();
    }
    public void setChkOffers(){
        chkOffers.click();
    }

    public void setTxtFirstName(String firstName){
        txtFirstName.sendKeys(firstName);
    }
    public  void setTxtlastName(String lastName){
        txtlastName.sendKeys(lastName);
    }
    public  void setTxtCompany(String companyName){
        txtCompany.sendKeys(companyName);
    }
    public void setTxtAddress1(String address1){
        txtAddress1.sendKeys(address1);
    }
    public void setTxtAddress2(String address2){
        txtAddress2.sendKeys(address2);
    }
    public void setDrpCountry(String country){
        selectDropdown(drpCountry,country);
    }
    public void setTxtState(String state){
        txtState.sendKeys(state);
    }
    public void setTxtCity(String city){
        txtCity.sendKeys(city);
    }
    public void setTxtZipCode(String zipCode){
        txtZipCode.sendKeys(zipCode);
    }
    public void setTxtMobile(String mobile){
        txtMobile.sendKeys(mobile);
    }

    public void setBtnCreateAcc(){
        btnCreateAcc.click();
    }

    public String setMsgConfirmation(){
        try{
            return msgConfirmation.getText();
        } catch (Exception e) {
            return (e.getMessage());
        }
    }

}
