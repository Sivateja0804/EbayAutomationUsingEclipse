package Pages;

import Utilities.variablePaths;
import Utilities.wrapperClass;

import org.apache.log4j.Logger;

import Utilities.captureScreen;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class LoginPage {
    wrapperClass wrapperClass=null;
    captureScreen screen=null;
    Logger logger= null;
    
    /**
     * Default constructor for initializing wrapper class,logger and capturescreenshots objects etc.
     *
     * @return null
     * @param null
     */

    public LoginPage(){
        super();
         wrapperClass=new wrapperClass();
         screen=new captureScreen();
         logger = Logger.getLogger(CheckOutPage.class.getName());;
    }

    /**
     * @Param androidDriver
     * @return boolean
     * This method is used to validate the Signin page
     */

    public boolean ValidateSigninPage(AppiumDriver<AndroidElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.signIn, null);
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.signIn, null);
            if (wrapperClass.webElementOperations(androidDriver, "isdisplayed","id",variablePaths.userName,variablePaths.userName_details)){
                logger.info("Clicked the sign in Button successfully and validated sign in page successfully");
            	return true;
            }else{
            	logger.info("Validation of sign in page Failed");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("SignInError");
            logger.error("validation of signIn page failed with exception "+ex);
            return false;
        }
    }

    /**
     * @Param androidDriver
     * @return boolean
     * This method is used to validate the Signin page functionalities such as logging in
     */

    public boolean validateSignInFunctionality(AppiumDriver<AndroidElement> androidDriver) throws Exception{
        try{
            wrapperClass.webElementOperations(androidDriver, "text","id",variablePaths.userName,variablePaths.userName_details);
            wrapperClass.webElementOperations(androidDriver, "text","id",variablePaths.password,variablePaths.password_details);
            wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.signIn, null);
            wrapperClass.webElementOperations(androidDriver, "wait","id",variablePaths.noThanks, null);
            if(wrapperClass.webElementOperations(androidDriver, "isdisplayed","id",variablePaths.noThanks, null)){
                screen.takeScreenshot("Nothanks");
                wrapperClass.webElementOperations(androidDriver, "click","id",variablePaths.noThanks, null);
                logger.info("Validaiton of Sign page functionalities is successfuk");
                return true;
            }else{
                screen.takeScreenshot("SignInFuncFail");
                logger.info("Validation of sign in page functionalities failed");
                return false;
            }
        }catch (Exception ex){
            screen.takeScreenshot("SignInFuncError");
            logger.error("Validation of Sign in Page functionalities gave exception "+ex);
            return false;
        }
    }
}
