package com.example.petstore;


public class FeedListener implements Listener {     // 함수형 인터페이스 Listner를 구현함.

    int feedCriteria = 0;           // 급료기준 attribute

    public FeedListener(int criteria) {     // 음식 이벤트를 받기 위한 기능 제공
        this.feedCriteria = criteria;
    }

    @Override
    public void energyChanged(int energy) {     // 에너지 변동에 따른 펫의 반응 구현.
        if(energy < feedCriteria){
            System.out.println("please feed some food.");
        }
    }
    
    
}
