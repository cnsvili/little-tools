package com.littlenb.tools.httpclient;

import com.littlenb.tools.http.DefaultTrustManager;
import org.apache.http.HttpHost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * @author svili
 **/
public class HttpClientFactory {

    private HttpClientBuilder builder = HttpClients.custom();

    public CloseableHttpClient create() {
        return builder.build();
    }

    public HttpClientFactory proxy(String hostname, int port) {
        return proxy(hostname, port, null);
    }

    public HttpClientFactory proxy(String hostname, int port, String scheme) {
        HttpHost proxy = new HttpHost(hostname, port, scheme);
        builder.setProxy(proxy);
        return this;
    }

    public HttpClientFactory SSLContext() {
        X509TrustManager trustManager = DefaultTrustManager.getInstance();

        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("SSLv3");
            sslContext.init(null, new TrustManager[]{trustManager}, null);
        } catch (NoSuchAlgorithmException e) {
            // getInstance error.
            throw new RuntimeException(e);
        } catch (KeyManagementException e) {
            // init error.
            throw new RuntimeException(e);
        }

        return SSLContext(sslContext);
    }

    public HttpClientFactory SSLContext(SSLContext sslContext) {
        // 设置协议http和https对应的处理socket链接工厂的对象
        Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.INSTANCE)
                .register("https", new SSLConnectionSocketFactory(sslContext)).build();
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

        builder.setConnectionManager(connManager);
        return this;
    }

}
