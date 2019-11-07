package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

public class SearchPage {
    WebDriver driver;
    public SearchPage(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    @FindBys({@FindBy(xpath="//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-medium']//following-sibling::div[@class='sg-row']//a[@class='a-link-normal a-text-normal']")})
    @CacheLookup
    List<WebElement> bestSellerProducts;

    @FindAll({@FindBy (xpath="//h4[contains(.,'Added to Cart')][2]"),
            @FindBy(xpath = "//*[contains(text(),'Added to Cart')]")})
    @CacheLookup
    List<WebElement> addedToCartMessage;

    @FindBy(xpath = "//*[contains(text(),'Added to Cart')]")
    @CacheLookup
    List<WebElement> cartAdditionMessageInNewPage;

    @FindBy(id = "add-to-cart-button")
    @CacheLookup
    WebElement addToCart;

    @FindBy(id = "nav-cart-count")
    @CacheLookup
    WebElement cartCount;

    public List<WebElement> bestSellerProducts() {
        return bestSellerProducts;
    }

    public List<WebElement> addCartMessageText(){
        return addedToCartMessage;
    }

    public void checkForCartButton(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("hlb-view-cart-announce")));
    }

    public List<WebElement> cartAdditionSuccessInNewPage(){
        return cartAdditionMessageInNewPage;
    }

    public void checkForAddToCartButton(){
        new WebDriverWait(driver,30).until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));

    }
    public void addToCart() {
        addToCart.click();
    }


    public Integer getCartItemCount() {
        return Integer.valueOf(cartCount.getText());
    }
}
