package com.wonders.sys.utils;

import com.wonders.common.utils.JacksonUtil;
import com.wonders.common.utils.RequestUtil;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public class WechatTokenUtils {

    public static String getWxAccessToken(){

        ResponseEntity<String> responseEntity = RequestUtil.httpRequest("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxdc9953f125fd61af&secret=699503d825266333ff0addb7ab106c70",
                "",null, HttpMethod.GET);
        Map<String,String> tokenParam = JacksonUtil.readValue(responseEntity.getBody(),Map.class);

        return tokenParam.get("access_token");
    }
}
