package com.example.petstore.domain;


import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity     // JPA가 관리하는 클래스로, 해당 클래스를 엔티티라고 하겠다~
public class CartItem {
    @Id   @GeneratedValue   // 해당 프로퍼티가 PK(기본키)이다 + 자동 생성 전략 명시
    Long id;

    // CartItem의 입장에서 매니투원, 원투매니 같은 어노테이션은 loosely connected된 것임.
    @ManyToOne      // 연관관계를 매핑
    Customer customer;

    String pet;    // 문자열로 URI(해당 펫에 대한 주소)을 그냥 받아준다.
        // pet의 getter&setter
        public String getPet() { return pet; }
        public void setPet(String pet) { this.pet = pet; }

    // 임베디드는 강한 결합을 의미함.
    @Embedded
    Payment payment;

    // id, customer, payment의 getter&setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
    public Payment getPayment() { return payment; }
    public void setPayment(Payment payment) { this.payment = payment; }

}