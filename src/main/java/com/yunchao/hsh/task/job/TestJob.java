package com.yunchao.hsh.task.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.ejb.Schedule;

/**
 * @Authher:Zhai Qing
 * @Date: 2018/11/21 17:43
 * @Description:
 * @Email: hkwind959@gmail.com
 */
@Component("testJob")
public class TestJob {

    private Logger logger = LoggerFactory.getLogger(TestJob.class);

//    @Scheduled(cron = "*/5 * * * * ?")
    public void job1() {
        System.out.println("任务进行中。。。");
    }

}
