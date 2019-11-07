package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;
    public HomePage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBy(id="twotabsearchtextbox")
    @CacheLookup
    WebElement txtSearchBox;

    @FindBy(className="nav-input")
    @CacheLookup
    WebElement btnSeach;

    public void setTextSearchString(String searchString) {
        txtSearchBox.sendKeys(searchString);
    }
    public void clickSearchButton() {
        btnSeach.click();
    }
}
