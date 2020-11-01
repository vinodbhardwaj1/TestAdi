package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {

    private static final Logger logger = LoggerFactory.getLogger(PropertiesHelper.class);
    public static Properties properties = null;
    private PropertiesHelper(){}

    private static Properties getPropertiesInstance() {
        properties = new Properties();
        try {
            File file = new File("./src/test/resources/properties/application.properties");
            Properties prop = new Properties();
            prop.load(new FileInputStream(file));

            properties.putAll(prop);
            logger.info("Properties loaded.");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String key) {
        if (properties == null) {
            synchronized (PropertiesHelper.class) {
                properties = getPropertiesInstance();
            }
        }
        return properties.getProperty(key).trim();
    }
}
