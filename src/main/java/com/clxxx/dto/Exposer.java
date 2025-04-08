package com.clxxx.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class Exposer {
    //是否开启秒杀
    private boolean exposed;
    //加密
    private String md5;

    private Long seckillId;
    //当前时间
    private Long now;
    private Long start;
    private Long end;

    public Exposer(boolean exposed, String md5, Long seckillId) {
        this.exposed = exposed;
        this.md5 = md5;
        this.seckillId = seckillId;
    }

    public Exposer(boolean exposed, Long now, Long start, Long end) {
        this.exposed = exposed;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, Long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }
}
