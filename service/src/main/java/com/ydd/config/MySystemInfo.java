package com.ydd.config;

import com.vimalselvam.testng.SystemInfo;

import java.util.HashMap;
import java.util.Map;

public class MySystemInfo implements SystemInfo {
    @Override
    public Map<String, String> getSystemInfo() {
//        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("env.properties");
//        Properties properties = new Properties();
        Map<String, String> systemInfo = new HashMap<>();
        try {
//            properties.load(inputStream);
//            systemInfo.put("environment", properties.getProperty("Environment"));
//            systemInfo.put("sqlURL", properties.getProperty("ESsql.URL"));
//            systemInfo.put("redisHost", properties.getProperty("redis.host"));
//            systemInfo.put("redisPort", properties.getProperty("redis.port"));
//            systemInfo.put("mongodbHost", properties.getProperty("mongodb.host"));
//            systemInfo.put("mongodbPort", properties.getProperty("mongodb.port"));
            systemInfo.put("测试人员", "jxq");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systemInfo;
    }
}