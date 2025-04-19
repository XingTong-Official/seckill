package com.clxxx.Controller;

import com.clxxx.dto.Exposer;
import com.clxxx.dto.SeckillExecution;
import com.clxxx.dto.SeckillResult;
import com.clxxx.entity.SecKill;
import com.clxxx.enums.SeckillEnum;
import com.clxxx.exception.RepeatKillException;
import com.clxxx.exception.SeckillCloseException;
import com.clxxx.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seckill")
public class SeckillController {
    private final Logger logger= LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SeckillService seckillService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public String list(Model model){
        List<SecKill> allSeckill = seckillService.getAllSeckill();
        model.addAttribute("list",allSeckill);
        return "list";
    }
    @RequestMapping(value = "/{seckillId}/detail",method =RequestMethod.GET)
    public String detail(@PathVariable("seckillId")Long seckillId,Model model){
        if(seckillId==null){
            return "redirect:/seckill/list";
        }
        SecKill seckillById = seckillService.getSeckillById(seckillId);
        if(seckillById==null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckillById);
        return "detail";
    }
    @RequestMapping(value = "/{seckillId}/exposer",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SeckillResult<Exposer> seckillResult;
        try {
            Exposer exposer = seckillService.exportSeckillUrl(seckillId);
            seckillResult=new SeckillResult<Exposer>(true,exposer);
        }
        catch (Exception e){
            logger.error(e.getMessage(),e);
            seckillResult=new SeckillResult<Exposer>(false,e.getMessage());
        }
        return seckillResult;
    }
    @RequestMapping(value = "/{seckillId}/{md5}/execution}",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,@PathVariable("md5") String md5,@CookieValue(value = "phone",required = false) Long phone){
        if(phone==null) return new SeckillResult<SeckillExecution>(false,"未注册");
        try {
            SeckillExecution execution=seckillService.executeSeckill(seckillId,phone,md5);
            return new SeckillResult<SeckillExecution>(true,execution);
        }
        catch (RepeatKillException e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillEnum.REPEAT_KILL);
            return new SeckillResult<SeckillExecution>(false,execution);
        }
        catch (SeckillCloseException e){
            logger.error(e.getMessage(),e);
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillEnum.END);
            return new SeckillResult<SeckillExecution>(false,execution);
        }
        catch (Exception e) {
            logger.error(e.getMessage(),e);
            SeckillExecution execution=new SeckillExecution(seckillId,SeckillEnum.INNER_ERROR);
            return new SeckillResult<SeckillExecution>(false,execution);
        }
    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time(){
        Date now=new Date();
        return new SeckillResult<Long>(true,now.getTime());
    }
}
