package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer price;

    private String content;

    //FetchType 은 LAZY , EAGER  두가지가 있음.
    // LAZY =지연로딩(연관 테이블 get메소드를 호출 하지 않으면 연계테이블 미호출),
    // EAGER=즉시로딩(연관 설정된 테이블을 조인상태로 쿼리가 수행됨) --> 성능저하가 발생 될 수 있음. 1:1일때 호출함.
    // LAZY select * from item where id = ? 로 해당 테이블만 우선 조회를 함.
    // EAGER item_id = order_detail.item_id and user_id = order_detail.user_id where item.id = ? 형식으로 조인형태로 쿼리수행.
    // orderDetail : item -> N : 1 임으로 list로 가져와야됨. mappedBy는 설정된 변수값으로
    @OneToMany(fetch = FetchType.LAZY , mappedBy = "item")
    private List<OrderDetail> orderDetailList;
}
