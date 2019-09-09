package com.wonders.sys.enums;

import lombok.Getter;
import lombok.Setter;

import static com.wonders.sys.authorize.SessionManage.AUTHORIZATION;


/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description :
 */
public enum ResultMessage {

    LOGIN_ERROR("登录失败"),
    LOGIN_SUCCESS("登录成功"),
    ACCOUNT_NOEXIST("账户不存在"),
    PASSWORD_ERROR("密码错误"),
    ACCOUNT_LOCKED("该用户已被冻结"),

    AUTHORIZE_ERROR("权限验证失败"),
    NOT_AUTHORIZE("无请求权限"),
    TOKEN_INVALID(AUTHORIZATION+"失效"),

    QUERY_SUCCESS("查询成功"),
    QUERY_ERROR("查询失败"),
    DELETE_SUCCESS("删除成功"),
    DELETE_ERROR("删除失败，请稍后重试"),
    SAVE_SUCCESS("保存成功"),
    SAVE_ERROR("保存失败，请稍后重试");


    @Getter@Setter
    private String message;
    ResultMessage(String message) {
        this.message = message;
    }
}
