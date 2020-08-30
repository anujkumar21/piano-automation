package com.piano.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyFilereader {

    public static Properties prop = new Properties();

    public static Properties getProperty(String filename) throws IOException {

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + File.separator + filename);
        prop.load(fis);

        return prop;
    }

}
