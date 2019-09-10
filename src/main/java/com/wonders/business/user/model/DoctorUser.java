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
public class DoctorUser extends BaseModel{

    private String userId;
    private String hospital;
    private String position;
    private String label;

}
