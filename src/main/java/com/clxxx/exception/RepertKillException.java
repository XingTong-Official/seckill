package com.clxxx.exception;

/**
 * 重复秒杀异常
 */
public class RepertKillException extends RuntimeException{
    public RepertKillException(String message) {
        super(message);
    }

    public RepertKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
