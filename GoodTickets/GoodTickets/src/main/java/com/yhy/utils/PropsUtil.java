package com.yhy.utils;

import java.io.InputStream;
import java.util.Properties;

// 解析 属性文件
public class PropsUtil {

    private final static String CONFIG_LOCATION_PREFIX = "classpath:";

    /**
     * @param configLocation classpath:db.properties
     */
    public static Properties readConfig(String configLocation) {
        Properties props = new Properties();
        try {
            String filename = configLocation.replace(CONFIG_LOCATION_PREFIX, "");
            InputStream stream = PropsUtil.class.getClassLoader().getResourceAsStream(filename);
            props.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return props;
    }

}
