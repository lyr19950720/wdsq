package com.wjl.wdsq.aspect;

import com.wjl.wdsq.controller.IndexController;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class logaspect {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
    @Before("execution(* com.wjl.wdsq.controller.IndexController.*(..))")
    public  void  beforeMethod()
    {
           logger.info("before method");
    }
    @After("execution(* com.wjl.wdsq.controller.IndexController.*(..))")
    public  void  afterMethod()
    {
        logger.info("after method");
    }
}
