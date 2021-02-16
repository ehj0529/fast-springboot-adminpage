package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity //== table
//@Table(name ="user") --> 테이블명과 엔티티명이 동일 하므로 생략가능
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String account;

    private String email;

    private String phoneNumber;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    // user : orderDetail 의 관계는 1 : N
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "user") // 변수명은 orderDateil의 변수명과 동일 해야 됨.
    private List<OrderDetail> orderDetailList;
}
