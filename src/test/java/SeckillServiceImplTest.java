import com.clxxx.dto.Exposer;
import com.clxxx.dto.SeckillExecution;
import com.clxxx.entity.SecKill;
import com.clxxx.exception.RepeatKillException;
import com.clxxx.exception.SeckillCloseException;
import com.clxxx.service.SeckillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
})
public class SeckillServiceImplTest {
    @Autowired
    public SeckillService seckillService;
    @Test
    public void getAllSeckill() {
        List<SecKill> allSeckill = seckillService.getAllSeckill();
        for (SecKill secKill:allSeckill){
            System.out.println(secKill);
        }
    }

    @Test
    public void getSeckillById() {
        SecKill seckillById = seckillService.getSeckillById(new Long(1000));
        System.out.println(seckillById);
    }

    @Test
    public void exportSeckillUrl() {
        Exposer exposer = seckillService.exportSeckillUrl(new Long(1000));
        System.out.println(exposer);
    }

    @Test
    public void executeSeckill() {
        String md5="asdasdaaweqf";
        try {
            seckillService.executeSeckill(new Long(1000),12345678910L,md5);
        }
        catch (RepeatKillException e1){
            System.out.println(e1.getMessage());
        }
        catch (SeckillCloseException e2){
            System.out.println(e2.getMessage());
        }
        catch (Exception e3){
            System.out.println(e3.getMessage());
        }
    }
    @Test
    public void testLogic(){
        Long Id=1000L;
        Exposer exposer=seckillService.exportSeckillUrl(Id);
        if(exposer.isExposed()){
            Long userphone=12345678910L;
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(Id, userphone, exposer.getMd5());
                System.out.println(seckillExecution);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        else {
            System.out.println("Not Start");
        }
    }
}