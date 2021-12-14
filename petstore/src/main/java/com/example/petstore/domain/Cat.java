package com.example.petstore.domain;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.example.petstore.Groomable;

@Entity     // JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 하겠다~
@DiscriminatorValue("cat")      // 부모 클래스에 구분 컬럼이다. 이 컬럼으로 저장된 자식 테이블을 구분 할 수 있다. 기본값은 cat이다.
public class Cat extends Pet implements Groomable, Runnable{        // Cat은 Pet을 상속받고 있으며(다형성) Groomable, Runnable 인터페이스를 명세해주어야 한다.

    public Cat() {
        setType("Cat");
    }

    @Override       // Pet 클래스의 내용을 보다 구체화하는 오버라이드
    public void speak() {       // 고양이의 말하기
        if(getEnergy() < 5){    // 에너지가 5보다 작으면 배고프다고 말한다.
            System.out.println("I'm hungry");
        }else                   // 그 밖의 경우는 안녕이라고 말한다.
            System.out.println("Hi");
    }

    @Override        // Pet 클래스의 내용을 보다 구체화하는 오버라이드
    public String grooming() {      // 고양이만 할 수 있는 그루밍
        //answer must be obtained by UI
        setAppearance(getAppearance() + 1);
        return "야옹. 행복하다 집사야.";
    }

    @Override
    public void run() {            
        System.out.println("I'm running!!!");
    }

}
