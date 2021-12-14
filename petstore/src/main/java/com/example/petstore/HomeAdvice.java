package com.example.petstore;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class HomeAdvice {

    static int pageView = 0;            // 누적 방문 attribute
    public static int getPageView(){ return pageView; }     // pageView의 getter
    
    @After("execution(* com.example.petstore.PetApplication.*(..))")       // 반복되는 준비 작업을 별도의 메소드에 넣게 해 주고, 이를 매번 테스트 메소드를 실행하기 전에 먼저 자동으로 실행시켜주는 기능
    public void after(){
        System.out.println("--- page view :  " + (++pageView));     // 방문 시 pageView attribute를 증가시킴
    }

}
