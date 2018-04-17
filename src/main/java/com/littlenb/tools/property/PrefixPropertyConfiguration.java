package com.littlenb.tools.property;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author svili
 **/
public class PrefixPropertyConfiguration extends PropertyConfiguration {

    private String prefix;

    public PrefixPropertyConfiguration(String prefix, String path) {
        super(path);
        this.prefix = prefix;
    }

    @Override
    public String get(String key, String defaultValue) {
        String completeKey = prefix + key;
        return super.get(completeKey, defaultValue);
    }

    @Override
    public Map<String, String> getKeyValuePairs() {
        Map<String, String> keyValuePairs = super.getKeyValuePairs();

        Map<String, String> prefixPairs = new HashMap<>(keyValuePairs.size());
        for (Map.Entry<String, String> entry : keyValuePairs.entrySet()) {

            if (entry.getKey().startsWith(prefix)) {
                prefixPairs.put(entry.getKey().replace(prefix, ""), entry.getValue());
            }

        }
        return Collections.unmodifiableMap(prefixPairs);
    }
}
