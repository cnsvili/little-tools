package com.littlenb.tools.http;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

/***
 * URI构建器
 *
 * @author svili
 *
 */
public class URIBuilder {

    /**
     * @param url    url or uri</br>
     *               e.g. https://www.baidu.com or
     *               https://www.baidu.com?param_1=value...
     * @param params if name or value isEmpty,it will be not append to URI.</br>
     * @return URI
     */
    public static String build(String url, Map<String, String> params) {
        String uri = url;
        if (params != null && !params.isEmpty()) {
            StringBuilder urlParam = new StringBuilder();
            for (Entry<String, String> entry : params.entrySet()) {
                String name = entry.getKey();
                String value = entry.getValue();
                if (!StringUtils.isEmpty(name) && !StringUtils.isEmpty(value)) {
                    urlParam.append("&");
                    urlParam.append(name).append("=");
                    // there are not use for encode
                    urlParam.append(value);
                }
            }

            if (url.contains("?")) {
                uri = url + urlParam.toString();
            }

            if (!url.contains("?")) {
                uri = url + urlParam.replace(0, 1, "?").toString();
            }

        }
        return uri;
    }

    public static String encode(String url) {
        String encodeURL = "";
        try {
            encodeURL = URLEncoder.encode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // 这里不会有异常
        }
        return encodeURL;
    }

}
