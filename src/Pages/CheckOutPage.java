package Pages;

import Utilities.variablePaths;
import Utilities.wrapperClass;

import org.apache.log4j.Logger;

import Testcase.Testcase;
import Utilities.captureScreen;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class CheckOutPage {
    wrapperClass wrapperClass=new wrapperClass();
    captureScreen screen=new captureScreen();
    Logger logger = null;
    
    
    /**
     * Default constructor for initializing wrapper class,logger and capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */

    public CheckOutPage(){
        super();
         wrapperClass=new wrapperClass();
         screen=new captureScreen();
         logger = Logger.getLogger(CheckOutPage.class.getName());;
    }

    /**
     * @Param androidDriver
     * @return boolean
     * This method is used to validate the checkout page functionalities
     */

    public boolean validationCheckoutPage(AppiumDriver<AndroidElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.reviewBtn, null);
            wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.tvCheckoutName, null);
            screen.takeScreenshot("checkoutpage");
            variablePaths.tvCheckoutNameText =wrapperClass.getText(androidDriver,"id",variablePaths.tvCheckoutName);
            if (!variablePaths.tvCheckoutNameText.isEmpty()){
            	logger.info("Clicked review button and Validation of Checkout page is successful");
                return true;
            }else{
                screen.takeScreenshot("checkoutFailure");
                logger.info("Validation of checkout page Failed");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("checkoutException");
            logger.error("Exception in Validation of Check out page "+ex);
            return false;
        }
    }

    /**
     * @Param null
     * @return boolean
     * This method is used to validate the TV name while purchasing and in checkout page
     */
    public boolean validateItemDetails(){
        if (variablePaths.tvNameText.equalsIgnoreCase(variablePaths.tvCheckoutNameText)){
        	logger.info("TV name and Check out name are Same, Validatio successfull");
            return true;
        }else{
        	logger.info("TV name and Check out name are not Same, Validatio Failed");
            return  false;
        }
    }
}
