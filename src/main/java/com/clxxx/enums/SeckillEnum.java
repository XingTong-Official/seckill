package com.clxxx.enums;

import lombok.Getter;

public enum SeckillEnum {
    SUCCESS(1,"秒杀成功") ,
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改")
    ;
    @Getter
    private int state;
    @Getter
    private String stateInfo;

    SeckillEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }
    public static SeckillEnum stateOf(int index){
        for (SeckillEnum state:values()){
            if(state.getState()==index){
                return state;
            }
        }
    }

}
