package com.example.petstore.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity     // // JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 하겠다~
@DiscriminatorValue("dog")      // 부모 클래스에 구분 컬럼이다. 이 컬럼으로 저장된 자식 테이블을 구분 할 수 있다. 기본값은 dog이다.
public class Dog extends Pet implements Runnable {      // Dog클래스는 Pet을 상속받으며(다형성) Runnable을 구현한다

    @Override
    public void speak() {       // 강아지의 말하기
        System.out.println("멍멍");
    }

    @Override
    public void run() {         
        System.out.println(" Dog Run!!!");
        
    }

    @Override
    public void eat() {         // 먹이를 먹을시 energy값을 1상승 시켜준다.
        setEnergy(getEnergy() + 1);
    }

    @Override
    public void sleep() {       // 잠을 잘 시, 강아지의 외모가 1 상승한다.
        super.sleep();          // 상위클래스의 sleep()메서드를 그대로 반영한다
        setAppearance(getAppearance() + 1);
    }

}
