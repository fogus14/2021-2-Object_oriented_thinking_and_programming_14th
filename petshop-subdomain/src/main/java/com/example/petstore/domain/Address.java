package com.example.petstore.domain;

import javax.persistence.Embeddable;

// Value Object -> 하나의 값을 나타내는 클래스이다.
@Embeddable         // 설정한 클래스가 다른 Entity의 일부로 저장될 수 있음을 설정. 즉, 다른 Entity에 포함될 수 있다는 의미(나는 따라다니는 놈이야~)
public class Address {  // 고객의 주소에 대한 클래스

    String detail;      // 고객의 상세주소를 저장하는 String
    String Zipcode;     // 고객의 Zipcode를 저장하는 String

    // 아래는 detail과 zipcode의 setter&getter
    public String getDetail() { return detail; }    
    public void setDetail(String detail) { this.detail = detail; }
    public String getZipcode() { return Zipcode; }
    public void setZipcode(String zipcode) { Zipcode = zipcode; }

}
