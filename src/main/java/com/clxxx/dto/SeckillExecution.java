package com.clxxx.dto;

import com.clxxx.entity.SuccessKilled;
import com.clxxx.enums.SeckillEnum;
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
    public SeckillExecution(Long seckillId, SeckillEnum state) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
    }
    public SeckillExecution(Long seckillId, SeckillEnum state, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
        this.successKilled = successKilled;
    }

    public SeckillExecution(Long seckillId, SeckillEnum state, String stateInfo) {
        this.seckillId = seckillId;
        this.state = state.getState();
        this.stateInfo = state.getStateInfo();
    }
}
