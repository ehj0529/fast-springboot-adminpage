package com.example.study.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString(exclude = {"user","item"}) // N:N으로 참조됨으로 오버플로우가 발생. 롬복이 toString() 자동생성때문. 그거를제거.
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime orderAt;

    // orderDetail : user 의 관계는 N:1 관계임.
    @ManyToOne
    private User user;//user_id가 자동 설정 된다. 하이버네이트에서 제공.

    @ManyToOne
    private Item item;

}
