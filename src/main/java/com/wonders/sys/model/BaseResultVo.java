package com.wonders.sys.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

public class BaseResultVo<T> implements Serializable {

    public BaseResultVo() {
        this.returnTime = LocalDateTime.now();
    }

    private int statusCode;

    private String message;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:SS", shape = JsonFormat.Shape.STRING)
    private LocalDateTime returnTime;

    private T data;

    private long count;

}
