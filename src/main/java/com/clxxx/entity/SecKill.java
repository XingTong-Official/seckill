package com.clxxx.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.sql.Date;
@Setter @Getter @ToString
public class SecKill {
    Integer seckill_Id;
    Integer number;
    String name;
    Date create_Time;
    Date start_Time;
    Date end_Time;
}
