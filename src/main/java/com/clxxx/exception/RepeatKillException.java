package com.clxxx.exception;

/**
 * 重复秒杀异常
 */
public class RepeatKillException extends RuntimeException{
    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
