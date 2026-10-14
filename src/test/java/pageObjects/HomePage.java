package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    //Constructor
    /*public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
     */
    public HomePage(WebDriver driver){
        super(driver);
    }

    //Locators
    @FindBy(xpath="//a[normalize-space()='Signup / Login']")
    WebElement lnkLoginSignup;

    //Action Methods
    public void clickSignup(){
        lnkLoginSignup.click();
    }
}
