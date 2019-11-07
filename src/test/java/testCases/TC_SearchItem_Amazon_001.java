package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObject.HomePage;
import pageObject.SearchPage;
import utils.XLUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TC_SearchItem_Amazon_001 extends BaseClass{

    @Test(dataProvider = "SearchData")
    public void seachItem(String textToSearch) throws IOException {

        logger.info("URL is opened");
        HomePage home = new HomePage(driver);
        logger.info("Opened the browser");

        if(driver.getTitle().toLowerCase().equals(title.toLowerCase())){
            Assert.assertTrue(true);
        }
        else{
            captureScreenshot(driver,"SearchItem");
            logger.error("Navigation to Amazon web page failed");
            Assert.assertTrue(false);
        }

        home.setTextSearchString(textToSearch);
        logger.info("Searched for "+ textToSearch);
        home.clickSearchButton();

        SearchPage search = new SearchPage(driver);

        List<WebElement> bestSellerProductList = search.bestSellerProducts();
        for (int i=0;i<bestSellerProductList.size();i++){
            search = new SearchPage(driver);
            List <WebElement> productRefLinks = search.bestSellerProducts();
            productRefLinks.get(i).click();
            logger.info("Clicked on product");
            search.addToCart();
            logger.info("clicked on add to cart button");
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            List<WebElement> cartAdditionMessage = search.addCartMessageText();
            if(cartAdditionMessage.size()!=0){
                Assert.assertTrue(true);
                logger.info("Item is added to cart");
            }
            else{
                logger.error("Item is not added to cart");
                Assert.assertTrue(false);
            }


            driver.navigate().back();
            if(driver.findElements(By.xpath("//span[text()='Best Seller']/ancestor::div[@class='a-section a-spacing-medium']//following-sibling::div[@class='sg-row']//a[@class='a-link-normal a-text-normal']")).size()==0)
                driver.navigate().back();
        }

        }

    @DataProvider(name="SearchData")
    Object[][] getData() throws IOException {
        String path=System.getProperty("user.dir")+"/src/test/java/testData/SearchData.xlsx";
        int rowNumber= XLUtils.getRowCount(path,"Sheet1");
        int columnCount=XLUtils.getCellCount(path,"Sheet1",1);

        String loginData[][]=new String[rowNumber][columnCount];
        for (int i=1;i<=rowNumber;i++){
            for(int j=0;j<columnCount;j++){
                loginData[i-1][j]=XLUtils.getCellData(path,"Sheet1",i,j);
            }
        }
        return loginData;
    }
}
