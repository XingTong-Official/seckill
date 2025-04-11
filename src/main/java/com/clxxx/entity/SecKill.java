package com.clxxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;
@Setter @Getter @ToString
public class SecKill {
    Long seckillId;
    Long number;
    String name;
    Date createTime;
    Date startTime;
    Date endTime;
}
