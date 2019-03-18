package Pages;


import Utilities.variablePaths;
import Utilities.wrapperClass;

import org.apache.log4j.Logger;

import Utilities.captureScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ProductPage {
    wrapperClass wrapperClass=null;
    captureScreen screen=null;
    Logger logger=null;
    
    /**
     * Default constructor for initializing wrapper class,logger and capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */

    public ProductPage(){
        super();
         wrapperClass=new wrapperClass();
         screen=new captureScreen();
         logger = Logger.getLogger(ProductPage.class.getName());;
    }

    /**
     * @Param androidDriver
     * @return boolean
     * This method is used to validate the searchItem page functionalities
     */
    public boolean searchItem(AppiumDriver<AndroidElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.searchBox, null);
            wrapperClass.webElementOperations(androidDriver, "text","id",variablePaths.searchBox_text, variablePaths.searchText);
            wrapperClass.webElementOperations(androidDriver, "randomclick","id",variablePaths.tvList, null);
            if (wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.buyNow, null)){
                screen.takeScreenshot("searchItemPass");
                logger.info("Searching item from the list passed");
                return true;
            }else{
                screen.takeScreenshot("searchItemFail");
                logger.info("searching item from the list failed");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("searchItemError");
            logger.error("Exception in searching item from the list "+ex);
            return false;
        }
    }

    /**
     * @Param androidDriver
     * @return boolean
     * This method is used to validate the buy item functionalities
     */

    public boolean buyItem(AppiumDriver<AndroidElement> androidDriver) throws Exception{
        try{
            variablePaths.tvNameText =wrapperClass.getText(androidDriver,"id",variablePaths.tvName);
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.buyNow, null);
            if(wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.reviewBtn, null)){
                screen.takeScreenshot("BuyItemPass");
                logger.info("Buying the item is Successful");
                return true;
            }else{
                screen.takeScreenshot("buyItemFail");
                logger.info("Buying item failed");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("buyItemError");
            logger.error("Exception raised while buying item "+ex);
            return false;
        }
    }
}
