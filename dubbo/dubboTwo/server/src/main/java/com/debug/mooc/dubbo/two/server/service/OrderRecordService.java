package com.debug.mooc.dubbo.two.server.service;

import com.debug.mooc.dubbo.two.server.data.DubboRecordResponse;
import com.debug.mooc.dubbo.two.server.request.RecordPushRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author coderpwh
 * @create 2019-06-09 13:57
 * @desc ${DESCRIPTION}
 **/
@Service
public class OrderRecordService {

    private static final Logger log= LoggerFactory.getLogger(OrderRecordService.class);

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private HttpService httpService;


    private static final String url="http://127.0.0.1:9013/v1/record/push";

    private OkHttpClient httpClient=new OkHttpClient();

    /**
     * 处理controller层过来的用户下单数据
     * @param pushRequest
     */
    public void pushOrder(RecordPushRequest pushRequest) throws Exception{
        try {
            //TODO:构造builder
            Request.Builder builder=new Request.Builder()
                    .url(url)
                    .header("Content-Type","application/json");

            //TODO:构造请求体
            RequestBody requestBody= RequestBody.create(MediaType.parse("application/json"),
                    objectMapper.writeValueAsString(pushRequest));

            //TODO:构造请求
            Request request=builder.post(requestBody).build();

            //TODO:发起请求
            Response response=httpClient.newCall(request).execute();


        }catch (Exception e){
            throw e;
        }
    }


    /**
     * 处理controller层过来的用户下单数据-采用通用化http服务类实战
     * @param pushRequest
     */
    public void pushOrderV2(RecordPushRequest pushRequest) throws Exception{
        try {
            Map<String,String> headerMap=new HashMap<>();
            headerMap.put("Content-Type","application/json");
            String res=httpService.post(url,headerMap,"application/json"
                    ,objectMapper.writeValueAsString(pushRequest));
            log.info("响应结果：{} ",res);

            //TODO:map解析-针对响应数据字段比较少的场景
//            Map<String,Object> resMap=objectMapper.readValue(res,Map.class);
//            log.info("得到的响应解析结果：{} ",resMap);
//            Integer code= (Integer) resMap.get("code");
//            String msg= (String) resMap.get("msg");
//            Integer data= (Integer) resMap.get("data");
//            log.info("code={} msg={} data={} ",code,msg,data);


            //TODO:对象解析-更加通用-数据字段比较多/复杂的解析
            DubboRecordResponse dubboRecordResponse=objectMapper.readValue(res,DubboRecordResponse.class);
            log.info("得到的响应解析结果：{} ",dubboRecordResponse);
            if (dubboRecordResponse.getCode().equals(0)){
                //TODO:属于自己的业务逻辑....

            }

        }catch (Exception e){
            throw e;
        }
    }
}
