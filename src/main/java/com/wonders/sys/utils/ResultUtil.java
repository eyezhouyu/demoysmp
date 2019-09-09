package com.wonders.sys.utils;

import com.wonders.business.user.basic.BaseResult;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description :
 */
public class ResultUtil {

    /**
     * 创建数据默认返回结果
     * @param t 返回数据
     * @param <T> 数据类型
     * @return BaseResult
     */
    public static <T> BaseResult getDataResult(T t, String message){
        BaseResult commonResult = new BaseResult<T>();
        commonResult.setData(t);
        commonResult.setMessage(StringUtils.isEmpty(message)?"查询成功":message);
        commonResult.setStatusCode(HttpStatus.OK.value());
        return commonResult;
    }

    /**
     * 创建数据默认返回结果
     * @param t 返回数据
     * @param count 数据类型
     * @param message 返回消息
     * @return BaseResult
     */
    public static <T> BaseResult getDataResult(T t,long count, String message){
        BaseResult commonResult = new BaseResult<T>();
        commonResult.setData(t);
        commonResult.setCount(count);
        commonResult.setMessage(StringUtils.isEmpty(message)?"查询成功":message);
        commonResult.setStatusCode(HttpStatus.OK.value());
        return commonResult;
    }

    /**
     * 创建无数据默认返回结果
     * @return BaseResult
     */
    public static BaseResult getNonDate(String message){
        BaseResult commonResult = new BaseResult();
        commonResult.setStatusCode(HttpStatus.OK.value());
        commonResult.setMessage(StringUtils.isEmpty(message)?"操作成功":message);
        return commonResult;
    }

    /**
     * getCodeNonData
     * @date 2019/3/15
     * @param httpStatus httpStatus
     * @param message message
     * @return top.ywhome.framework.basic.result.BaseResult
     **/
    public static BaseResult getCodeNonData(HttpStatus httpStatus,String message){
        BaseResult commonResult = new BaseResult();
        commonResult.setStatusCode(httpStatus.value());
        commonResult.setMessage(StringUtils.isEmpty(message)?"":message);
        return commonResult;
    }

    /**
     * getCodeData
     * @date 2019/5/14
     * @param t t
     * @param httpStatus httpStatus
     * @param message message
     * @return top.ywhome.framework.basic.result.BaseResult
     **/
    public static <T> BaseResult getCodeData(T t,HttpStatus httpStatus,String message){
        BaseResult commonResult = new BaseResult();
        commonResult.setData(t);
        commonResult.setStatusCode(httpStatus.value());
        commonResult.setMessage(StringUtils.isEmpty(message)?"":message);
        return commonResult;
    }

    /**
     * getFailNonData
     * @date 2019/5/14
     * @param message message
     * @return top.ywhome.framework.basic.result.BaseResult
     **/
    public static BaseResult getFailNonData(String message){
        BaseResult commonResult = new BaseResult();
        commonResult.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        commonResult.setMessage(StringUtils.isEmpty(message)?"":message);
        return commonResult;
    }
}
