package com.clxxx.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({
        "classpath:spring-dao.xml",
        "classpath:spring-service.xml"
})
public class SeckillServiceImplTest {

    @Test
    public void getAllSeckill() {
    }

    @Test
    public void getSeckillById() {
    }

    @Test
    public void exportSeckillUrl() {
    }

    @Test
    public void getMD5() {
    }

    @Test
    public void executeSeckill() {
    }
}