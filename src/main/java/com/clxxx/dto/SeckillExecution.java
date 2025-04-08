package com.clxxx.dto;

import com.clxxx.entity.SuccessKilled;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 封装秒杀完成后是否成功
 */
@Setter @Getter @ToString
public class SeckillExecution {
    private Long seckillId;
    private int state;
    private String stateInfo;
    private SuccessKilled successKilled;

    public SeckillExecution(Long seckillId, int state, String stateInfo, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
        this.successKilled = successKilled;
    }

    public SeckillExecution(Long seckillId, int state, String stateInfo) {
        this.seckillId = seckillId;
        this.state = state;
        this.stateInfo = stateInfo;
    }
}
