package com.ydd.config;

import com.vimalselvam.testng.SystemInfo;

import java.util.HashMap;
import java.util.Map;

public class MySystemInfo implements SystemInfo {
    @Override
    public Map<String, String> getSystemInfo() {
        Map<String,String> systemInfo = new HashMap<>();
        systemInfo.put("負責人","楊帆");
        return systemInfo;
    }
}
