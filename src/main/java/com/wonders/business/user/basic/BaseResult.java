package com.wonders.business.user.basic;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : zhouyu
 * @date : 2019/9/6
 * @description :
 */
@Data
public class BaseResult<T> implements Serializable {

    private static final long serialVersionUID = 5270483866708381581L;

    public BaseResult() {
        this.returnTime = LocalDateTime.now();
    }

    private int statusCode;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS",shape = JsonFormat.Shape.STRING)
    private LocalDateTime returnTime;

    private T data;

    private long count;

}
