package com.example.petstore.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity                     // JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 하겠다~
@DiscriminatorColumn(       // 엔티티를 저장할 때 구분컬럼에 입력할 값을 지정한다.
    discriminatorType = DiscriminatorType.STRING,      // 구분자의 타입
    name = "pet_type",                  // 구분자로 사용할 컬럼명
    columnDefinition = "CHAR(5)"        // 컬럼의 domain을 정한다 (캐릭터 5자...)
)
public abstract class Pet {      // Entity. Domain Class. (Cat과 Dog의 부모클래스이다.)

    @Id @GeneratedValue         // 해당 프로퍼티가 PK(기본키)이다 + 자동 생성 전략 명시
    Long id;    
        public Long getId() { return id; }

    private int appearance;     // 외모지수 attribute
        // 외모지수의 getter&setter
        public int getAppearance() { return appearance; }
        protected void setAppearance(int appearance) { this.appearance = appearance; }

    String name;                // 이름 attribute
        // 이름의 getter&setter
        public String getName() { return name; }
        public void setName(String name) {      // 이름을 일종의 not null타입이어야 하기 때문에 아래와 같은 로직 삽입.
            if(name==null) throw new IllegalArgumentException("이름은 꼭 들어가야 합니다");
            this.name = name;
        }

    String type;                // 애완동물의 타입 attribute
        // 애완동물의 타입의 getter&setter
        public String getType() { return type; }
        public void setType(String type) { this.type = type; }

    abstract public void speak();       // 각 하위클래스에서 말하기와 관련된 내용을 구체화할 수 있도록 추상클래스의 사용.

    // List<Listener> listeners = new ArrayList<Listener>();
    // public void addListener(Listener listener) {
    //     this.listeners.add(listener);
    // }

    private int energy = 0;             // 에너지와 관련한 attribute
        public int getEnergy() { return energy; }
        protected void setEnergy(int energy) {      // 애완동물이 갑자기 에너지가 늘거나 줄 수는 없기 때문에 일종의 로직을 부여함.
            if(Math.abs(this.energy - energy) < 3 )
                this.energy = energy;
            else    
                throw new IllegalArgumentException("Energy change is too big");
        }

    public void eat(){      // 하위 클래스에서 오버라이드 가능하며, 기본은 에너지 1 증가.
        energy += 1;
        // if(listeners!=null){
        //     for(int i = 0; i<listeners.size(); i++){
        //         listeners.get(i).energyChanged(energy);
        //     }
        // }
    }

    public void sleep(){    // 하위 클래스에서 오버라이드 가능하며, 기본은 에너지 1 증가.
        energy += 2;
    }

    //////////////////////////////////////////////////////////
    // 외모지수 추가 (12주차 과제) -> 내가 한 것.
    // private int appearance = 0;
    //     public int getAppearance() {
    //         return appearance;
    //     }
    //     public void setAppearance(int appearance) {
    //         this.appearance = appearance;
    //     }
    //////////////////////////////////////////////////////////

    @Override
    public String toString() {      // 객체가 가지고 있는 정보들을 문자열로 리턴해줌.
        return "<a href='./"+this.getClass().getSimpleName().toLowerCase()+"'" + ">" + this.getClass().getSimpleName() + "</a>";
    }
    
}
