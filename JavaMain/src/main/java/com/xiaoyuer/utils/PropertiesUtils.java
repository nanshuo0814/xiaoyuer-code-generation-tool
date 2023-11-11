package com.xiaoyuer.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesUtils {
    private static final Properties PROPS = new Properties();
    private static final Map<String, String> PROPER_MAP = new ConcurrentHashMap<String, String>();

    static {
        InputStream is = null;
        try {
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            PROPS.load(new InputStreamReader(is, "utf-8"));
            for (Object o : PROPS.keySet()) {
                String key = (String) o;
                PROPER_MAP.put((String) key, (String) PROPS.get(key));
            }
        } catch (Exception e) {

        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String getString(String key) {
        return PROPER_MAP.get(key);
    }

    public static void main(String[] args) {
        System.out.println(getString("db.url"));
    }
}
