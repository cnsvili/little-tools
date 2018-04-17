package com.littlenb.tools.property;

import java.util.Map;

/**
 * @author svili
 **/
public interface PropertyConfigurable {

    String get(String key);

    String get(String key, String defaultValue);

    Map<String,String> getKeyValuePairs();
}
