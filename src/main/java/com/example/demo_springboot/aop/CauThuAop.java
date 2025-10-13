package com.example.demo_springboot.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CauThuAop {

    private static int count=0;
    private static int countList=0;
    @After("execution(* com.example.demo_springboot.service.CauThuService.addCauThu(..))")
    public void countAddCauThu(){
        count++;
        System.out.println("=========Tong so lan them cau thu"+ count +"============");
    }
    @After("execution(* com.example.demo_springboot.service.CauThuService.findAll(..))")
    public void countList(){
        countList++;
        System.out.println("===========Tong so lan xem list"+ countList +"============");
    }
}
