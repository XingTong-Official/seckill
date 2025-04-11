package com.clxxx.service;

import com.clxxx.dao.SeckillDao;
import com.clxxx.dao.SuccessKilledDao;
import com.clxxx.dto.Exposer;
import com.clxxx.dto.SeckillExecution;
import com.clxxx.entity.SecKill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
@Service
public class SeckillServiceImpl implements SeckillService{
    @Autowired
    SeckillDao seckillDao;
    @Autowired
    SuccessKilledDao successKilledDao;
    String salt="buhuabanshudeyanxianbuyongmoyunfendiye";
    @Override
    public List<SecKill> getAllSeckill() {
        return seckillDao.queryAll();
    }

    @Override
    public SecKill getSeckillById(Long id) {
        return seckillDao.queryById(id);
    }

    @Override
    public Exposer exportSeckillUrl(Long id) {
        SecKill secKill = seckillDao.queryById(id);
        if(secKill == null||secKill.getNumber()<=0){
            return new Exposer(false,id);
        }
        Date now=new Date();
        Date start=secKill.getStartTime();
        Date end=secKill.getEndTime();
        if(now.getTime()>end.getTime()||now.getTime()<start.getTime()){
            return new Exposer(false,id,now.getTime(),start.getTime(),end.getTime());
        }
        String md5=getMD5(id);
        return new Exposer(true,md5,id);
    }
    String getMD5(Long id){
        String base=id+"/"+salt;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

    @Override
    public SeckillExecution executeSeckill(Long seckillId, Long userphone, String md5) throws Exception {
        return null;
    }
}
