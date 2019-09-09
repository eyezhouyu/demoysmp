package com.wonders.common.utils;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

/**
 * @author : zhouyu
 * @date : 2019/9/6
 */
public class RequestUtil {

    /**
     * httpRequest
     * @date 2019/5/7
     * @param url url
     * @param body body
     * @param headers headers
     * @param httpMethod httpMethod
     * @return org.springframework.http.ResponseEntity<java.lang.String>
     **/
    public static ResponseEntity<String> httpRequest(String url, Object body, @Nullable HttpHeaders headers, @Nullable HttpMethod httpMethod) {
        String bodyEntity;
        //header 处理
        if (ObjectUtils.isEmpty(headers)){
            headers = new HttpHeaders();
        }
        if (ObjectUtils.isEmpty(headers.getContentType())){
            headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        }
        //httpMethod 请求请求方式处理
        if(ObjectUtils.isEmpty(httpMethod)){
            httpMethod = HttpMethod.POST;
        }
        //body 请求参数处理
        if (String.class.isAssignableFrom(body.getClass())){
            bodyEntity = body.toString();
        }else {
            bodyEntity = JacksonUtil.toJSon(body);
        }
        HttpEntity<String> request = new HttpEntity<>(bodyEntity, headers);
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.exchange(url,httpMethod,request,String.class);
    }

}
