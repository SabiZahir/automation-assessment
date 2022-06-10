package com.labcorp.config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static Properties properties;
    private static final String CONFIG_FILE_PATH = "src/main/resources/config.properties";

    //hiding the constructor so this class cannot be instantiated
    private ConfigReader(){}

    static {
        try {
            FileInputStream inputStream = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(inputStream);
            inputStream.close();
        }catch (Exception e){

        }
    }

    public static String getProperty(String keyName){
        return properties.getProperty(keyName);
    }

    public static void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}
