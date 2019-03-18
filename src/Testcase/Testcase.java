package Testcase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import Pages.CheckOutPage;
import Capabilities.DesiredCapabilities;
import Pages.LoginPage;
import Pages.ProductPage;
import io.appium.java_client.MobileElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class Testcase extends DesiredCapabilities {

    WebDriverWait webDriverWait = null;
    WebElement webElement = null;
    WebDriver driver = null;
    AppiumDriver <AndroidElement> androidDriver=null;
    LoginPage loginPage=new LoginPage();
    ProductPage productPage=new ProductPage();
    CheckOutPage checkOutPage=new CheckOutPage();
    Logger logger = null;

    @BeforeTest
    public  void initialization() {
        androidDriver=super.getDriver();
        Assert.assertNotNull(String.valueOf(androidDriver) , "Asertion Error : Unable to get the initialized Driver for automation");
        logger = Logger.getLogger(Testcase.class.getName());
        logger.info("Initialisation has successfully completed");
    }

    @Test
    public void execution() throws Exception{
    	try {
    		//sign in to EBay App
            Assert.assertTrue("Assertion Error : Error Navigating to Login Page", loginPage.ValidateSigninPage(androidDriver));
            Assert.assertTrue("Assertion Error : Error Validating the Sign in functionality", loginPage.validateSignInFunctionality(androidDriver));

            //Buying a product
            Assert.assertTrue("Assertion Error : Error Validating the Search item Page",productPage.searchItem(androidDriver));
            Assert.assertTrue("Assertion Error : Error Buying the item",productPage.buyItem(androidDriver));

            //validating checkout page
            Assert.assertTrue("Assertion Error : Error validating the checkout page",checkOutPage.validationCheckoutPage(androidDriver));
            Assert.assertTrue("Assertion Error : Error validating the checkout page item details",checkOutPage.validateItemDetails());
        
    	}catch(Exception ex) {
    		logger.error("Test case Execution failed due to exception "+ex);
    	}
        }

    @AfterTest
    public void cleanup(){
        try{
            androidDriver.quit();
        }catch (Exception ex){
            System.out.println("Exception occured in cleanup" + ex);
        }
    }
}