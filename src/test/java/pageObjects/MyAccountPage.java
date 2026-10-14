package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

    //Constructor
    public MyAccountPage(WebDriver driver){
        super(driver);
    }

    //Loctors
    @FindBy(xpath="//a[normalize-space()='Logout']")
    WebElement btnLogout;

    //Action Methods
    public void clickLogout(){
        btnLogout.click();
    }

    public boolean isLogoutExist(){
        try{
            return btnLogout.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }
}
