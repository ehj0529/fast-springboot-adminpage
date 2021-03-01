package com.example.study.model.entity;

import com.example.study.model.enumclass.OrderType;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString(exclude = {"user","orderDetailList"})
@EntityListeners(AuditingEntityListener.class)  //감시자엔티티 리스너를 사용한다.
@Builder  //  객체생성할때 적용 생성자라 각각 필요하지 않음. .builder().account("Test1").password("123").build()의 형식으로 적용.
@Accessors(chain = true) // Update 시 엔티티 변경을 셋 할때 빌드생성자와 같은 개념으로 user.setEmail().setStatus() 형식으로 생성한다.
public class OrderGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    private String status ;

    @Enumerated(EnumType.STRING)
    private OrderType orderType ;  // 주문의 형태 - 일괄 / 개별

    private String revAddress ;

    private String revName ;

    private String paymentType ; //카드 현금 이체

    private BigDecimal totalPrice ;

    private Integer  totalQuantity ;

    private LocalDateTime orderAt ;

    private LocalDateTime arrivalDate ;

    @CreatedDate //엔티티의 값들이 수정이되면 자동으로 값에 셋팅 된다. 이하 컨트럴 필드 모두
    private LocalDateTime createdAt ;

    @CreatedBy
    private String createdBy ;

    @LastModifiedDate
    private LocalDateTime updatedAt ;

    @LastModifiedBy
    private String updatedBy ;

    // OrderGroup N : 1 User
    @ManyToOne
    private User user ;

    //OrderGroup 1 : N OrderDetail
    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "orderGroup")
    private List<OrderDetail> orderDetailList;

}
