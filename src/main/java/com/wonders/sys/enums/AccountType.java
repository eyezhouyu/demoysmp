package com.wonders.sys.enums;

import lombok.Getter;
import lombok.Setter;

public enum AccountType {
    DOCTOR("0"),
    PATIENT("1");

    @Getter@Setter
    private String type;

    AccountType(String type){
        this.type = type;
    }
}
