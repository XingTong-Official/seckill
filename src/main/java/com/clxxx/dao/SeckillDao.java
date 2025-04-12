package com.clxxx.dao;

import com.clxxx.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.sql.Date;
import java.util.List;
public interface SeckillDao {
    int reduceNumber(@Param("seckillId") Long seckill_Id, @Param("killTime") Date killTime);
    SecKill queryById(@Param("seckillId") Long seckill_Id);
    List<SecKill> queryAll(@Param("offset") Integer offset, @Param("limit") Integer limit);
    List<SecKill> queryAll();
}
