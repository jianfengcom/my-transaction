package com.example.transaction.cac.demo;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;

public class RequestDemo {

    /*
        请求超时时间
        这个时间定义了socket读数据的超时时间，也就是连接到服务器之后到从服务器获取响应数据需要等待的时间
        发生超时，会抛出SocketTimeoutException异常
     */
    private static final int SOCKET_TIME_OUT = 60000;
    /*
        连接超时时间
        这个时间定义了通过网络与服务器建立连接的超时时间，也就是取得了连接池中的某个连接之后到接通目标url的连接等待时间
        发生超时，会抛出ConnectionTimeoutException异常
     */
    private static final int CONNECT_TIME_OUT = 60000;

    @Test
    public void test() throws IOException {
        String url = "http://localhost:8080/d/show";
        String json = "{\"list\":[\"{'time':'xxxxx','distinct_id':'xxxx','appId':'xxxx'}\",\"{'time':'xxxxx','distinct_id':'xxxx','appId':'xxxx'}\",\"{'time':'xxxxx','distinct_id':'xxxx','appId':'xxxx'}\",\"{'time':'xxxxx','distinct_id':'xxxx','appId':'xxxx'}\"],\"type\":1}";

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost method = new HttpPost(url);

        // 设置配置
        RequestConfig reqConfig = RequestConfig.custom()
                .setSocketTimeout(SOCKET_TIME_OUT) // 设置请求超时时间
                .setConnectTimeout(CONNECT_TIME_OUT) // 设置连接超时时间
                .build();
        method.setHeader("Content-type", "application/json"); // 不指定, 报 "Unsupported Media Type","message":"Content type 'text/plain;charset=UTF-8' not supported"
        method.setHeader("Accept", "application/json");
        method.setConfig(reqConfig);

        // 设置请求参数
        StringEntity entity = new StringEntity(json, "UTF-8");
        method.setEntity(entity);

        CloseableHttpResponse response = httpClient.execute(method);
        HttpEntity retEntity = response.getEntity();

        String result = EntityUtils.toString(retEntity, "UTF-8");
        System.out.println(result);
    }
}
