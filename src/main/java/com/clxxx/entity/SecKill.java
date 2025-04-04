package com.clxxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;
@Setter @Getter @ToString
public class SecKill {
    Integer seckillId;
    Integer number;
    String name;
    Date createTime;
    Date startTime;
    Date endTime;
}
