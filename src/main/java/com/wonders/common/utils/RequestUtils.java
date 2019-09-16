package com.wonders.common.utils;

import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

public class RequestUtils {

    public static <T> ResponseEntity<T> httpRequest(String url, Object body, @Nullable HttpHeaders headers, @Nullable HttpMethod httpMethod, Class<T> clazz) {
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
        return restTemplate.exchange(url,httpMethod,request,clazz);
    }
}
