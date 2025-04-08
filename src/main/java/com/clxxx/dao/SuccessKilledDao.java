package com.clxxx.dao;

import com.clxxx.entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

public interface SuccessKilledDao {
    int insertSuccessKilled(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") Long seckillId, @Param("userPhone") Long userPhone);
}