package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

    //Constructor
    public LoginPage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(xpath="//input[@data-qa='login-email']")
    WebElement txtLoginEmail;

    @FindBy(xpath="//input[@placeholder='Password']")
    WebElement txtLoginPassword;

    @FindBy(xpath="//button[normalize-space()='Login']")
    WebElement btnLogin;

    //Action Methods
    public void setTxtLoginEmail(String email){
        txtLoginEmail.sendKeys(email);
    }

    public void setTxtLoginPassword(String password){
        txtLoginPassword.sendKeys(password);
    }

    public void clickLogin(){
        btnLogin.click();
    }
}
