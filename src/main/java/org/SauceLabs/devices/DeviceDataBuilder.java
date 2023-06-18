package org.SauceLabs.devices;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class DeviceDataBuilder {

    public static IOSDevice registerIOSDevice() throws FileNotFoundException {
        JSONParser parser = new JSONParser();

        try {
            Object obj = parser.parse(new FileReader("src/main/java/org/SauceLabs/devices/iOS-Devices.json"));
            JSONObject jsonObject = (JSONObject)obj;

            return IOSDevice
                    .builder()
                    .PlatformName((String)jsonObject.get("PlatformName"))
                    .PlatformVersion((String)jsonObject.get("PlatformVersion"))
                    .DeviceName((String)jsonObject.get("DeviceName"))
                    .build();

        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
