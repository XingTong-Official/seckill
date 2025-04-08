package com.clxxx.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Setter @ToString @Getter
public class SuccessKilled {
    private Byte state;

    private Date createTime;

    private Long seckillId;

    private Long userPhone;

    // 多对一,因为一件商品在库存中有很多数量，对应的购买明细也有很多。
    private SecKill seckill;
}
