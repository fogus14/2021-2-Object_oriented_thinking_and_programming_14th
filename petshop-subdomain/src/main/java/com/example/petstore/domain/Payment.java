package com.example.petstore.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Payment {

    String method;      // 결제 방법을 의미하는 변수
    String status;      // 결제 상태를 의미하는 변수

    // method와 status의 getter&setter
    public String getMethod() { return method; }
    public void setMethod(String method) { this.method = method; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

}
