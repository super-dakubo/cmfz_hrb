package com.baizhi.conf;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Logs4admin;
import com.baizhi.service.Logs4adminService;
import com.baizhi.util.GoeasyUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Configuration
@Aspect
public class log4AdminAop {
    @Autowired
    private Logs4adminService logs4adminService;
    @Autowired
    private HttpServletRequest request;
    @Pointcut("@annotation(log4edit)")
    public void pointcut(Log4edit log4edit){}

    @Around("pointcut(log4edit)")
    public Object testlog(ProceedingJoinPoint point,Log4edit log4edit){
        //什么人
        Admin admin = (Admin) request.getSession().getAttribute("login");
        if(admin==null)
            admin = new Admin("11", "yy", "123456");
        //什么时间
        Date date = new Date();
        //什么事
        String methodName = log4edit.value();
        System.out.println("***************************"+methodName);
        try {
            Object proceed = point.proceed();
            //成功标记
            Logs4admin logs4admin = new Logs4admin(UUID.randomUUID().toString(),admin.getUsername(),date,methodName,"成功");
            //记录日志
            logs4adminService.insert(logs4admin);
            GoeasyUtil.sendMessage("true");
            return proceed;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            //失败标记
            Logs4admin logs4admin = new Logs4admin(UUID.randomUUID().toString(),admin.getUsername(),date,methodName,"失败");
            //记录日志
            logs4adminService.insert(logs4admin);
            return null;
        }
    }
}
