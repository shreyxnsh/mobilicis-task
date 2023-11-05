package com.spacester.mobilicis.device;

import android.content.Context;
import android.os.Build;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DeviceInfo {

    private String manufacturer;
    private String model;
    private String ram;

    public DeviceInfo(Context context) {
        this.manufacturer = Build.MANUFACTURER;
        this.model = Build.MODEL;
        this.ram = getTotalRAM();
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getModel() {
        return model;
    }

    public String getRam() {
        return ram;
    }


    private String getTotalRAM() {
        long installedMemory = 0;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/meminfo"));
            String memoryLine;
            while ((memoryLine = bufferedReader.readLine()) != null) {
                if (memoryLine.contains("MemTotal:")) {
                    installedMemory = Long.parseLong(memoryLine.replaceAll("\\D+", ""));
                    break;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return installedMemory / (1024 * 1024) + " GB";
    }

}
