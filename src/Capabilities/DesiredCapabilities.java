package Capabilities;

import java.net.URL;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.android.AndroidElement;


public class DesiredCapabilities {
      public static AppiumDriver<AndroidElement> androidDriver = null;
      org.openqa.selenium.remote.DesiredCapabilities desiredCapabilities = null;

    /**
     * @Param null
     * @return androidDriver
     * This method is used to set the desired capabilities
     */
    public  AppiumDriver<AndroidElement> getDriver(){
        try{
            desiredCapabilities =new org.openqa.selenium.remote.DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel API 24");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.ebay.mobile");
            desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.ebay.mobile.activities.MainActivity");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, MobilePlatform.ANDROID);
            this.androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),desiredCapabilities);
            return this.androidDriver;
        }catch (Exception ex){
            System.out.println("Exception in initialising Android Driver" +ex);
            return null;
        }
    }
}
