package com.wonders.sys.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键ID")
    private String id;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @ApiModelProperty(hidden = true)
    private LocalDateTime updateTime;

    /**
     * 创建人
     */
    @ApiModelProperty(hidden = true)
    private String creator;

    /**
     * 更新人
     */
    @ApiModelProperty(hidden = true)
    private String updateUser;

}
