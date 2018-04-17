package com.littlenb.tools.http;

import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/***
 * 默认的证书信任管理器,绕过SSL验证</br>
 * 静态内部类实现单例模式.
 *
 * @author svili
 *
 */
public class DefaultTrustManager implements X509TrustManager {

    /**
     * 私有化构造方法
     */
    private DefaultTrustManager() {
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }

    public static DefaultTrustManager getInstance() {
        return Holder.trustManager;
    }

    private static class Holder {
        private static DefaultTrustManager trustManager = new DefaultTrustManager();
    }

}
