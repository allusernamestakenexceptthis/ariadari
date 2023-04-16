package com.gomilkyway.profile.adari.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.fasterxml.jackson.core.util.VersionUtil;

public class AppVersionUtil {
        
    private static final String VERSION_FILE = "version.properties";
    private static final String VERSION_PROP = "version";

    public static String getVersion() {

        Properties props = new Properties();
        try (InputStream inputStream = VersionUtil.class.getClassLoader().getResourceAsStream(VERSION_FILE)) {
            if (inputStream != null) {
                props.load(inputStream);
                return props.getProperty(VERSION_PROP);
            }
        } catch (IOException e) {
            // ignore
        }

        return "unknown";
    }

}
