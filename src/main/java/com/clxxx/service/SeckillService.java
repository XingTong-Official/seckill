package com.clxxx.service;

import com.clxxx.dto.Exposer;
import com.clxxx.dto.SeckillExecution;
import com.clxxx.entity.SecKill;

import java.util.List;

public interface SeckillService {
    List<SecKill> getAllSeckill();
    SecKill getSeckillById(Long id);
    Exposer exportSeckillUrl(Long id);
    SeckillExecution executeSeckill(Long seckillId, Long userphone, String md5) throws Exception;

}



























