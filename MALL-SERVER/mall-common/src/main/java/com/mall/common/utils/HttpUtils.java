package com.mall.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Created with IntelliJ IDEA.
 * @Author: C-aranlism
 * @Date: 2020/9/21
 */
@Log4j2
public class HttpUtils {

    private static CloseableHttpClient httpclient;

    static {
        PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();
        connManager.setMaxTotal(20);
        connManager.setDefaultMaxPerRoute(10);
        httpclient = HttpClients.custom().setConnectionManager(connManager).build();
    }

    public static String get(String url, Map<String, String> params) throws IOException {
        List<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            requestParams.add(new BasicNameValuePair(key, params.get(key)));
        }
        String requestUrl;
        if (url.contains("?")) {
            requestUrl = url + "&" + URLEncodedUtils.format(requestParams, "UTF-8");
        } else {
            requestUrl = url + "?" + URLEncodedUtils.format(requestParams, "UTF-8");
        }
        System.out.println(requestUrl);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(10000).setConnectTimeout(10000)
                .setSocketTimeout(10000).build();
        HttpGet get = new HttpGet(requestUrl);
        get.setConfig(requestConfig);
        CloseableHttpResponse respond = httpclient.execute(get);
        String result = EntityUtils.toString(respond.getEntity(), "UTF-8");
        return result;
    }

    public static String post(String url, Map<String, String> params) throws IOException {
        HttpPost post = new HttpPost(url);
        ArrayList<NameValuePair> requestParams = new ArrayList<NameValuePair>();
        for (String key : params.keySet()) {
            requestParams.add(new BasicNameValuePair(key, params.get(key)));
        }
        post.setEntity(new UrlEncodedFormEntity(requestParams, "UTF-8"));
        CloseableHttpResponse respond = httpclient.execute(post);
        return EntityUtils.toString(respond.getEntity(), "UTF-8");
    }

    public static JSONObject postJSON(String url, Map<String, String> params) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-type", "application/json");
        post.setEntity(new StringEntity(JSON.toJSONString(params), "UTF-8"));
        CloseableHttpResponse respond = httpclient.execute(post);
        if (respond.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            JSONObject result = JSON.parseObject(EntityUtils.toString(respond.getEntity(), "UTF-8"));
            log.info(result);
            return result;
        } else {
            throw new RuntimeException("Http请求异常,请查看网管接口");
        }
    }

}
