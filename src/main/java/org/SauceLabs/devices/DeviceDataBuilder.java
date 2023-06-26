package org.SauceLabs.devices;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import java.io.FileNotFoundException;
import java.io.FileReader;


public class DeviceDataBuilder {

    private static final Logger LOGGER = LogManager.getLogger (
            "DeviceDataBuilder.class");

    public static IOSDevice registerIOSDevice(String deviceName) throws FileNotFoundException {
        JSONParser parser = new JSONParser();
        
        try (FileReader reader = new FileReader("src/test/resources/iOS-Devices.json")){
            JSONObject jsonObject = (JSONObject) parser.parse(reader);

            JSONArray devices = (JSONArray) jsonObject.get("devices");

            for (Object o : devices) {
                JSONObject device = (JSONObject) o;

                if (device.get("deviceName").equals(deviceName)) {

                    return IOSDevice
                            .builder()
                            .platformName((String) device.get("platformName"))
                            .platformVersion((String) device.get("platformVersion"))
                            .deviceName((String) device.get("deviceName"))
                            .build();
                }
            }


        } catch(Exception e) {
            LOGGER.error(e);
        }

        System.out.println("No device found with the name: " + deviceName);
        return null;
    }
}
