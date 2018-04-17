package com.littlenb.tools.httpclient;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/***
 * HttpClient 工具类.</br>
 * create <code>HttpUriRequest</code>.
 *
 * @author svili
 *
 */
public class HttpRequestBuilder {

    private HttpRequestBase request;

    public HttpRequestBuilder(String method) {
        switch (method) {
            case "PATCH":
                request = new HttpPatch();
                break;
            case "PUT":
                request = new HttpPut();
                break;
            case "POST":
                request = new HttpPost();
                break;
            case "GET":
                request = new HttpGet();
                break;
            default:
                request = new HttpGet();
                break;
        }
    }

    public HttpRequestBase build() {
        return request;
    }

    public HttpRequestBuilder uri(String uri) {
        request.setURI(URI.create(uri));
        return this;
    }


    public HttpRequestBuilder header(String name, String value) {
        request.addHeader(name, value);
        return this;
    }

    public HttpRequestBuilder header(Map<String, String> headers) {
        if (headers != null && !headers.isEmpty()) {
            for (Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
        return this;
    }

    public HttpRequestBuilder form(Map<String, String> formParams) {

        if (!(request instanceof HttpEntityEnclosingRequestBase)) {
            //
        }
        if (formParams != null && !formParams.isEmpty()) {
            // 创建参数列表
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(formParams.size());
            for (Entry<String, String> entry : formParams.entrySet()) {
                String name = entry.getKey();
                String value = entry.getValue();
                if (!StringUtils.isBlank(name)) {
                    if (!StringUtils.isBlank(value)) {
                        nameValuePairs.add(new BasicNameValuePair(name, value));
                    } else {
                        nameValuePairs.add(new BasicNameValuePair(name, ""));
                    }
                }
            }
            ((HttpEntityEnclosingRequestBase) request).setEntity(new UrlEncodedFormEntity(nameValuePairs, Charset.forName("UTF-8")));
        }
        return this;
    }

    public HttpRequestBuilder body(String body) {
        if (!StringUtils.isEmpty(body)) {
            ((HttpEntityEnclosingRequestBase) request).setEntity(new StringEntity(body, Charset.forName("UTF-8")));
        }
        // default application/json
        if (!request.containsHeader("Content-Type")) {
            request.addHeader("Content-Type", "application/json");
        }
        return this;
    }

    public HttpRequestBuilder binary(File file) {

        return binary(file, file != null ? file.getName() : null);
    }

    public HttpRequestBuilder binary(File file, String fileName) {

        MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();

        // 表单项为空,默认填充file
        entityBuilder.addBinaryBody("file", file, ContentType.DEFAULT_BINARY, fileName);

        return entity(entityBuilder.build());
    }


    public HttpRequestBuilder entity(HttpEntity entity) {
        ((HttpEntityEnclosingRequestBase) request).setEntity(entity);
        return this;
    }


}
