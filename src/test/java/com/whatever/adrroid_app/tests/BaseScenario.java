package com.whatever.adrroid_app.tests;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.net.URL;
import java.util.*;
import java.util.logging.Level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class BaseScenario {


    HashMap<String, String> devices = new HashMap<String, String>();
    protected AppiumDriver driver;
    AppiumServer appium = new AppiumServer();
    DesiredCapabilities dc = new DesiredCapabilities();

    Boolean withserver = true; // Start appium programatically

    String usedevice = "";


    @BeforeTest
    public void setup() throws Exception {
        String csvFile = "src/main/resources/devices.csv";
        String line = "";
        String cvsSplitBy = ",";


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] device = line.split(cvsSplitBy);

//                System.out.println("******Device [code= " + device[0] + " , status=" + device[1] + "]");
                devices.put(device[0], device[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Set set = devices.entrySet();
        Iterator iterator = set.iterator();
        while (iterator.hasNext()) {
            Map.Entry mentry = (Map.Entry) iterator.next();
            System.out.print("key is: " + mentry.getKey() + " & Value is: ");
            System.out.println(mentry.getValue());
            if (mentry.getValue().toString().contains("true")) {
                if (mentry.getKey().toString().contains("emulator")){appium.emulator=true;}
                usedevice = mentry.getKey().toString();
            }
        }

        if (withserver) {
            appium.startServer();
        }

        DesiredCapabilities dc = new DesiredCapabilities();



        dc.setCapability(MobileCapabilityType.UDID, usedevice);
        dc.setCapability(MobileCapabilityType.DEVICE_NAME, "Android");
        dc.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        //---- other test apps
        //dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.afollestad.materialdialogssample");
        //dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".MainActivity");

        //dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.yahoo.mobile.client.android.flickr");
        //dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".activity.MainActivity");

       // dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.tvptdigital.tripassist");
        //dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.mttnow.android.ada.modules.splash.SplashActivity");

        dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.trivago");
        dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.trivago.ui.main.MainActivity");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), dc);
        driver.setLogLevel(Level.WARNING);

    }

    @AfterTest
    public void tearDown() {
        driver.quit();
        if (withserver) appium.stopServer();
    }
}
