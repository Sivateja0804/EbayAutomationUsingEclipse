package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;

public class wrapperClass {
    int waitTime=20;
    int min=0;

    /**
     * @Param androidDriver,operation,type,path,text
     * @return boolean
     * This method is used to validate the basic web element functions
     */

    public boolean webElementOperations(AppiumDriver<AndroidElement> androidDriver, String operation, String type, String path, String text){
        WebElement element=null;
        try{
            if (operation.equalsIgnoreCase("click")){
                element=findElement(androidDriver,type,path);
                element.click();
            }
            else if(operation.equalsIgnoreCase("wait")){
                element=waitForElement(androidDriver,type,path);
            }
            else if(operation.equalsIgnoreCase("randomclick")){
                element=randomElement(androidDriver,type,path);
                element.click();
            }
            else if(operation.equalsIgnoreCase("text")){
                element=findElement(androidDriver,type,path);
                element.click();
                element.clear();
                element.sendKeys(text);
            }
            else if(operation.equalsIgnoreCase("isdisplayed")){
                element=waitForElement(androidDriver,type,path);
                if (element != null){
                    return element.isDisplayed();
                }else{
                    return false;
                }
            }

            if (element!=null){
                return true;
            }else{
                return false;
            }
        }catch (Exception ex){
            System.out.println("Exception occured in webElementOperations"+ ex);
            return false;
        }
    }

    /**
     * @Param androidDriver,type,path
     * @return awebelement
     * This method is used to find an web element
     */
    public WebElement findElement(AppiumDriver driver, String type, String path){
        WebElement element=null;
        try {
            if (type.equalsIgnoreCase("id")){
                element=driver.findElementById(path);
            }
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in findElement"+ ex);
            return null;
        }

    }

    /**
     * @Param androidDriver,type,path
     * @return webelement
     * This method is used to find a random element from a list
     */
    public WebElement randomElement(AppiumDriver driver,String type,String path){
        try{
            WebElement element=null;
            List<WebElement> elementlist=waitForElementList(driver,type,path);
            Dimension size =driver.manage().window().getSize();
            int param_len[]= new int[]{(int)(size.width*0.5),(int)(size.height*0.5),(int)(size.height*0.05)};
            TouchAction ta = new TouchAction(driver)
                    .longPress(param_len[0], param_len[1])
                    .waitAction(500)
                    .moveTo(param_len[0], param_len[2]);
            ta.release().perform();
            Thread.sleep(2000);
            elementlist=waitForElementList(driver,type,path);
            element=elementlist.get((int)(Math.random() * (elementlist.size()) + min));
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in randomElement"+ ex);
            return null;
        }
    }

    /**
     * @Param androidDriver,type,path
     * @return webelement
     * This method is used to wait for an element till it is visible
     */

    public WebElement waitForElement(AppiumDriver driver, String type, String path){
        WebElement element=null;
        try {
            WebDriverWait webDriverWait =new WebDriverWait(driver,waitTime);
            if (type.equalsIgnoreCase("id")){
                 element=webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(path)));
            }
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in waitForElement"+ ex);
            return null;
        }

    }

    /**
     * @Param androidDriver,type,path
     * @return String
     * This method is used to get the text of the web element
     */
    public String getText(AppiumDriver<AndroidElement> androidDriver, String type, String path){
        try{
            WebElement element=findElement(androidDriver,type,path);
            return element.getText();
        }catch (Exception ex){
           System.out.println("ERROR : Exception in get Text method "+ex);
           return  null;
        }
    }

    /**
     * @Param androidDriver,type,path
     * @return element
     * This method is used to wait till all the elements in the list are present
     */
    public List<WebElement> waitForElementList(AppiumDriver driver, String type, String path){
        List<WebElement> element=null;
        try {
            WebDriverWait webDriverWait =new WebDriverWait(driver,waitTime);
            if (type.equalsIgnoreCase("id")){
                element=webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(path)));
            }
            return element;
        }catch (Exception ex){
            System.out.println("Exception occured in waitForElement"+ ex);
            return null;
        }

    }
}
