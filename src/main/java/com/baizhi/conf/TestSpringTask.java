package com.baizhi.conf;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
@Component
@Async
public class TestSpringTask {
    @Scheduled(fixedDelay = 3000)
    public void testFixedDelay() throws InterruptedException {
        Thread.sleep(1000);
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        System.out.println("testFixedDelay :" + new Date());
    }
    @Scheduled(fixedRate = 5000)
    public void testFixedRate(){
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        System.out.println("testFixedRate :" +new Date());
    }
    @Scheduled(cron = "0/5 * * * * ?")
    public void testCron(){
        Thread thread = Thread.currentThread();
        System.out.println(thread);
        System.out.println("cron :" +new Date());
    }
}