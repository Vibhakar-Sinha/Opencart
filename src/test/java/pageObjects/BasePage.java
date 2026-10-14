package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class BasePage {

    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void selectDropdown(WebElement drpEle, String drpValue){
        drpEle.click();
        Select drp=new Select(drpEle);
        drp.selectByVisibleText(drpValue);
    }
}
