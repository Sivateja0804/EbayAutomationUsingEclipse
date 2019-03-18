package Utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

import Capabilities.DesiredCapabilities;

public class captureScreen extends DesiredCapabilities {

    /**
     * @Param String
     * @return null
     * This method is used to capture screenshots
     */
    public void takeScreenshot(String msg) throws IOException {
        String folder_name = "Screenshot";
        File file = ((TakesScreenshot)androidDriver).getScreenshotAs(OutputType.FILE);
        SimpleDateFormat timeStamp = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ss");
        new File(folder_name).mkdir();
        String screenShotName =msg+ timeStamp.format(new Date()) +".png";
        FileUtils.copyFile(file, new File(folder_name + "/" + screenShotName));
    }
}
