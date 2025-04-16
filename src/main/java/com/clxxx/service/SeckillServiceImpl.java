package com.clxxx.service;

import com.clxxx.dao.SeckillDao;
import com.clxxx.dao.SuccessKilledDao;
import com.clxxx.dto.Exposer;
import com.clxxx.dto.SeckillExecution;
import com.clxxx.entity.SecKill;
import com.clxxx.entity.SuccessKilled;
import com.clxxx.enums.SeckillEnum;
import com.clxxx.exception.RepeatKillException;
import com.clxxx.exception.SeckillCloseException;
import com.clxxx.exception.SeckillException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

@Service
public class SeckillServiceImpl implements SeckillService{
    // 日志对象
    private Logger logger = LoggerFactory.getLogger(this.getClass());
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
    @Transactional
    public SeckillExecution executeSeckill(Long seckillId, Long userphone, String md5) throws RepeatKillException, SeckillException,SeckillCloseException {
        if(md5==null || !md5.equals(getMD5(seckillId))){
            throw new SeckillException("seckill data rewrite");
        }
        Date nowTime= new Date();
        int updateCount = seckillDao.reduceNumber(seckillId,nowTime);
        try {
            if(updateCount<=0){
                //更新返回值不正确，应该为大于1的值，代表秒杀了几件
                throw new SeckillCloseException("seckill is closed");
            } else{
                int insertCount = successKilledDao.insertSuccessKilled(seckillId,userphone);
                if(insertCount<=0){
                    //重复秒杀
                    throw new RepeatKillException("seckill repeated");
                }
                else {
                    SuccessKilled successKilled=successKilledDao.queryByIdWithSeckill(seckillId,userphone);
                    return new SeckillExecution(seckillId, SeckillEnum.SUCCESS,successKilled);
                }
            }
        }catch (SeckillCloseException e1){
            throw e1;
        }
        catch (RepeatKillException e2){
            throw e2;
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            //转换成运行时异常,方便spring回滚
            throw new SeckillException("seckill inner error"+e.getMessage());
        }
    }
}
