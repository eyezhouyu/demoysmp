package com.wonders.business.user.model;


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
@ApiModel("医生用户对象")
public class PatientUser extends BaseModel {

    private String userId;
    private String hzgx;
    private String hbsc;
    private String sfjz;
    private String jzyymc;
    private String jzksmc;
    private String ywbqzp;
    private String sfzzsyyw;
    private String ywmc;
    private String ywzp;
    private String sfyzdjbs;
    private String zdgwbsms;
    private String sfygms;
    private String gmyw;
    private String bqms;
    private String bqzp;
    private String bqzp_2;
    private String examine;

}
