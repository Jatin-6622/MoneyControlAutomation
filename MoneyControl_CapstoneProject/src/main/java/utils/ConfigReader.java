package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private Properties prop;

    public Properties initProperties() {
        prop = new Properties();
        try {
            FileInputStream ip = new FileInputStream("src/main/resources/config.properties");
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public String getProperty(String key) {
        return prop.getProperty(key);
    }
}
