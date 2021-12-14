package com.example.petstore.domain;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

// Aggregate Root
@Entity
public class Customer {
    @Id             // 해당 프로퍼티가 PK(기본키)이다
    String id;

    @Embedded       // 객체지만 포인터로 참조하는 관계가 아니라 Customer에 데이터로 안고 다닐 것이다~
    Address address;

    // id, address의 getter&setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public Address getAddress() { return address; }
    public void setAddress(Address address) { this.address = address; }

    
}
