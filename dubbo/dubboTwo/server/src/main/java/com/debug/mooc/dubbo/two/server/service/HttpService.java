package com.debug.mooc.dubbo.two.server.service;

import com.google.common.base.Strings;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * http通信服务
 * @author:debug
 */
@Service
public class HttpService {

    private static final Logger log= LoggerFactory.getLogger(HttpService.class);

    private OkHttpClient client=new OkHttpClient();

    /**
     * 构造通用的get-request
     * @param url 请求路径
     * @param headerMap 请求头key-value
     * @return
     * @throws Exception
     */
    private Request commonGetRequest(String url,Map<String,String> headerMap) throws Exception{
        Request.Builder builder=new Request.Builder();

        Request request;
        if (headerMap!=null && headerMap.keySet()!=null && headerMap.keySet().size()>0){
            Headers headers=Headers.of(headerMap);
            request=builder.get()
                    .url(url)
                    .headers(headers)
                    .build();
        }else{
            request=builder.get()
                    .url(url)
                    .build();
        }
        return request;
    }

    private Request.Builder commonPostBuilder(String url, Map<String, String> headerMap) throws Exception {
        Request.Builder builder;
        if (headerMap != null && headerMap.keySet() != null && headerMap.keySet().size() > 0) {
            Headers headers = Headers.of(headerMap);
            builder = new Request.Builder()
                    .url(url)
                    .headers(headers);
        } else {
            builder = new Request.Builder()
                    .url(url);
        }
        return builder;
    }


    /**
     * get请求
     * @param url 请求url
     * @param headerMap 请求头map
     * @return 结果字符串
     */
    public String get(String url,Map<String,String> headerMap) throws Exception{
        Request request=commonGetRequest(url,headerMap);

        Response response;
        try {
            response=client.newCall(request).execute();
            return response.body().string();
        }catch (Exception e){
            log.error("发送同步-get请求发生异常：url={} ",e.fillInStackTrace());
        }
        return null;
    }

    /**
     * post请求
     * @param url 请求Url
     * @param headerMap 请求头map
     * @param contentType  请求内容类型
     * @param data 请求体数据-对象序列化后的字符串格式数据
     * @return 结果字符串
     */
    public String post(String url,Map<String,String> headerMap, String contentType,String data) throws Exception{
        Request.Builder builder=commonPostBuilder(url,headerMap);

        Request request;
        RequestBody requestBody;
        if (!Strings.isNullOrEmpty(data) && !Strings.isNullOrEmpty(contentType)){
            requestBody = RequestBody.create(MediaType.parse(contentType),data);
            request=builder.post(requestBody).build();
        }else {
            FormBody.Builder bodyBuilder=new FormBody.Builder();
            request=builder.post(bodyBuilder.build()).build();
        }

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * post请求--无请求体
     * @param url 请求Url
     * @return 结果字符串
     */
    public String post(String url) throws Exception{
        Request.Builder builder= new Request.Builder().url(url);
        FormBody.Builder bodyBuilder=new FormBody.Builder();
        Request request=builder.post(bodyBuilder.build()).build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * post请求
     * @param url
     * @param headerMap
     * @param bodyParams 请求体数据-map格式
     * @return
     * @throws Exception
     */
    public  String post(String url,Map<String,String> headerMap,Map<String,String> bodyParams) throws Exception{
        Request.Builder builder=commonPostBuilder(url,headerMap);

        RequestBody body=setRequestBody(bodyParams);
        Request request = builder
                .post(body)
                .url(url)
                .build();

        Response response;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * post请求
     * @param params
     * @return
     * 备注：form表单提交
     */
    private RequestBody setRequestBody(Map<String, String> params) {
        RequestBody body = null;
        FormBody.Builder formBuilder = new FormBody.Builder();
        if (params != null && params.keySet() != null && params.keySet().size() > 0) {
            String key;
            Iterator<String> iterator = params.keySet().iterator();
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                formBuilder.add(key, params.get(key));
            }
        }
        body = formBuilder.build();

        return body;
    }

}



















