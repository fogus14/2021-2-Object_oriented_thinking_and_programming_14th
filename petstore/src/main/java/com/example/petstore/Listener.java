package com.example.petstore;

//@FunctionalInterface 함수형인터페이스
public interface Listener {
    void energyChanged(int energy);     // 1개의 추상메서드만을 가짐. 에너지 값 변화와 관련...
}
