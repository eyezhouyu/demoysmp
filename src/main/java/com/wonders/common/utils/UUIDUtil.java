package com.wonders.common.utils;

import com.wonders.sys.model.BaseModel;

import java.util.UUID;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description : 主键生成策略
 */
public class UUIDUtil {
    /**
     * getUUID 简便id生成 添加前缀字段
     * @date 2019/1/31
     * @param e e
     * @return java.lang.String
     **/
    public synchronized static <E extends BaseModel> String getUUID(E e){
        StringBuilder builder =  new StringBuilder();
        if (e.getClass().getSimpleName().replaceAll("[a-z]+","").length()>=2){
            builder.append(e.getClass().getSimpleName().replaceAll("[a-z]+",""), 0, 2);
        }else {
            builder.append(e.getClass().getSimpleName().toUpperCase(), 0, 2);
        }
        builder.append(UUID.randomUUID().toString().replace("-",""));
        return builder.toString();
    }
}
