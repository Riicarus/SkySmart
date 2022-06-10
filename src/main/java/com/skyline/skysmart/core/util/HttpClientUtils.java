package com.skyline.skysmart.core.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * [FEATURE INFO]<br/>
 * http client utils
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
public class HttpClientUtils {

    /**
     * send http get request
     *
     * @param url String
     * @param param Map, get request params
     * @return String, result String
     */
    public static String doGet(String url, Map<String, String> param) {

        // create HttpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;

        try {
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // create http Get request
            HttpGet httpGet = new HttpGet(uri);

            // execute request
            response = httpClient.execute(httpGet);
            // judge response status
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response, httpClient);
        }

        return resultString;
    }

    /**
     * send http post request
     *
     * @param url String, url
     * @param param Map, post request params
     * @return String, result String
     */
    public static String doPost(String url, Map<String, String> param) {
        // create HttpClient object
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";

        try {
            // create HTTP Post request
            HttpPost httpPost = new HttpPost(url);
            // create param list
            if  (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // mock as form
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }

            // execute Http request
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response, httpClient);
        }

        return resultString;
    }

    /**
     * send http post request
     *
     * @param url String, url
     * @return String, result String
     */
    public static String doPost(String url) {
        return doPost(url, null );
    }

    /**
     * send http post request, param as Json
     *
     * @param url String, url
     * @param json String, param as Json
     * @return String, result String
     */
    public static String doPostJson(String url, String json) {
        // create HttpClient 对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // create HttpPost request
            HttpPost httpPost = new HttpPost(url);
            // create request param
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // execute http request
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(response, httpClient);
        }

        return resultString;
    }

    /**
     * close http client and response
     *
     * @param response CloseableHttpResponse
     * @param client CloseableHttpClient
     */
    private static void close(CloseableHttpResponse response, CloseableHttpClient client) {
        try {
            if (response != null) {
                response.close();
            }
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
