package org.SauceLabs.devices;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class IOSDevice {
    private String PlatformName;
    private String PlatformVersion;
    private String DeviceName;
}

