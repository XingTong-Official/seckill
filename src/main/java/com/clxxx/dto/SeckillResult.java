package com.clxxx.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
//封装ajax的json
@Setter@Getter@ToString
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String error;
    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String error) {
        this.success = success;
        this.error = error;
    }
}
