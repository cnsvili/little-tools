package com.littlenb.tools.property;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author svili
 **/
public class PropertyConfiguration implements PropertyConfigurable {

    protected final Logger logger = LoggerFactory.getLogger(PropertyConfiguration.class);

    protected Map<String, String> keyValuePairs = new HashMap<>();

    public PropertyConfiguration(String path) {
        Properties properties = new Properties();
        try (Reader reader = new InputStreamReader(PropertyConfiguration.class.getResourceAsStream(path), Charset.forName("utf-8"));) {
            properties.load(reader);
        } catch (IOException e) {
            logger.error("load property file error {} ", path, e);
        }

        for (Map.Entry<Object, Object> entry : properties.entrySet()) {
            keyValuePairs.put(String.valueOf(entry.getKey()), (String) entry.getValue());
        }
    }

    @Override
    public String get(String key) {
        return get(key, null);
    }

    @Override
    public String get(String key, String defaultValue) {
        String val = keyValuePairs.get(key);
        return (val == null) ? defaultValue : val;
    }

    @Override
    public Map<String, String> getKeyValuePairs() {
        return Collections.unmodifiableMap(keyValuePairs);
    }
}
