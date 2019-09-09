package com.wonders.sys.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : ywhome
 * @date : 2019/3/16 11:55
 * @description :
 */
public enum AccountStatus {
    //正常
    OK("ok"),
    //冻结
    LOCKED("locked"),
    //注销
    CANCEL("cancel");

    @Getter@Setter
    private String status;

    AccountStatus(String status) {
        this.status = status;
    }
}
