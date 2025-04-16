package com.clxxx.dao;

import com.clxxx.entity.SecKill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
public interface SeckillDao {
    int reduceNumber(@Param("seckillId") Long seckillId, @Param("killTime") Date killTime);
    SecKill queryById(@Param("seckillId") Long seckillId);
//    List<SecKill> queryAll(@Param("offset") Integer offset, @Param("limit") Integer limit);
    List<SecKill> queryAll();
}
