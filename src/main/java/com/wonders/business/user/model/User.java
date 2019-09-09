package com.wonders.business.user.model;

import com.wonders.sys.enums.AccountStatus;
import com.wonders.sys.enums.AccountType;
import com.wonders.sys.model.BaseModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * @description :
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel("基础用户对象")
public class User extends BaseModel{

    private String userName;
    private String password;
    private String nickName;
    private String realName;
    private String userIcon;
    private String userAddress;
    private String birthday;
    private String phone;
    private String gender;
    private String wechatNo;
    private String email;
    private String unionId;
    private String openId;
    private AccountStatus status;
    private AccountType userType;
}
