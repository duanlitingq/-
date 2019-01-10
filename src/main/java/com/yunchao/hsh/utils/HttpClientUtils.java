package com.yunchao.hsh.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import java.io.*;
import java.net.URI;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.*;

/**
 * @ClassName: HttpClientUtils
 * @Description: TODO
 * @Author: ZHAI Q
 * @Email:hkwind959@google.com
 * @Date: 2018/11/9 16:31
 * @Version: 1.0
 */
public class HttpClientUtils {

    public static final String CHARSET = "UTF-8";

    public static PoolingHttpClientConnectionManager connMgr;

    public static RequestConfig requestConfig;

    //超时时间
    public static final int MAX_TIMEOUT = 7000;

    /**
     * 不带参数的Get请求
     *
     * @param url
     * @return
     */
    public static String sendGet(String url) {
        return sendGet(url, null);

    }

    /**
     * 发送Get带参数请求
     *
     * @param url
     * @param param
     * @return
     */
    public static String sendGet(String url, Map<String, String> param) {
        CloseableHttpClient httpClient = null;
        //检测是否是https请求
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }

        String resultString = "";
        CloseableHttpResponse response = null;
        // 创建URI
        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                Set<String> keySet = param.keySet();
                for (String value : keySet) {
                    builder.addParameter(value, param.get(value));
                }
            }
            URI uri = builder.build();
            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 发起请求
            response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), CHARSET);
            } else {
                // 终止请求
                httpGet.abort();
                throw new RuntimeException(
                        "HttpClient -- ERROR--RESULTCODE:" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 发起POST请求
     * <p>
     * Title: sendPost
     * </p>
     */
    public static String sendPost(String url, Map<String, String> param) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        // 创建Httpclient对象
        CloseableHttpClient httpClient = null;
        // 检测是否是https请求
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<NameValuePair>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                // 创建Http Post请求
                if (paramList.size() > 0 && paramList != null) {
                    httpPost.setEntity(new UrlEncodedFormEntity(paramList, CHARSET));
                }
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), CHARSET);
            } else {
                httpPost.abort();
                throw new RuntimeException(
                        "HttpClient -- ERROR--RESULTCODE:" + response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 发起postJson请求
     * <p>
     * Title: sendPostJson
     * </p>
     */
    public static String sendPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = null;
        // 检测是否是https请求
        if (url.startsWith("https")) {
            httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
                    .setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        } else {
            httpClient = HttpClients.createDefault();
        }
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() != 200) {
                httpPost.abort();
                throw new RuntimeException(
                        "HttpClient -- ERROR--RESULTCODE:" + response.getStatusLine().getStatusCode());
            }
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }

    /**
     * 创建SSL安全链接
     * @return
     */
    public static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                @Override
                public boolean isTrusted(X509Certificate[] chain, String authType)
                        throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }

                @Override
                public void verify(String host, SSLSocket ssl) throws IOException {

                }

                @Override
                public void verify(String host, X509Certificate cert) throws SSLException {

                }

                @Override
                public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {

                }
            });
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }


    public static void main(String[] args) {

    }

}
