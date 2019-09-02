package com.whatever.adrroid_app.tests;

import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.service.local.flags.ServerArgument;
import org.openqa.selenium.remote.DesiredCapabilities;


public class AppiumServer {
    public AppiumDriverLocalService appiumService;
    public String appiumServiceUrl;

    AppiumDriverLocalService service;
    DesiredCapabilities cap;
    Boolean emulator = true;

    public void startServer() {


//Set Capabilities
        cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.NO_RESET, false);


//Build the Appium service
        AppiumServiceBuilder builder = new AppiumServiceBuilder();

        builder.withIPAddress("127.0.0.1");
        builder.usingPort(4723);
        if (emulator) {
            builder.withArgument(new ServerArgument() {
                public String getArgument() {
                    return "--avd";
                }
            }, "TestAvd");
        }


        builder.withCapabilities(cap);

        builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
        builder.withArgument(GeneralServerFlag.LOG_LEVEL, "info");

//Start the server with the builder
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        System.out.println("***Start appium service***");
    }

    public void stopServer() {
        service.stop();
        System.out.println("***Stop appium service***");
    }
}
