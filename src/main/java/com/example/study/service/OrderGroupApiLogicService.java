package com.example.study.service;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.entity.OrderGroup;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.OrderGroupApiRequest;
import com.example.study.model.network.response.OrderGroupApiResponse;
import com.example.study.repository.OrderGroupRepository;
import com.example.study.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body =  request.getData();

        System.out.println("user_id :: "+userRepository.getOne(body.getUserId()));

        OrderGroup orderGroup = OrderGroup.builder()
                .status(body.getStatus())
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(LocalDateTime.now())
                .user(userRepository.getOne(body.getUserId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);

        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {

        return orderGroupRepository.findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("조회 주문 내역이 없음"));

    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {

        OrderGroupApiRequest body = request.getData();

        return orderGroupRepository.findById(body.getId())
                .map( orderGroup -> { orderGroup.setStatus(body.getStatus())
                        .setOrderType(body.getOrderType())
                        .setRevAddress(body.getRevAddress())
                        .setRevName(body.getRevName())
                        .setPaymentType(body.getPaymentType())
                        .setTotalPrice(body.getTotalPrice())
                        .setTotalQuantity(body.getTotalQuantity())
                        .setOrderAt(body.getOrderAt())
                        .setArrivalDate(body.getArrivalDate())
                        .setUser(userRepository.getOne(body.getUserId()));
                    return orderGroup;

                })
                .map( changeOrderGroup -> orderGroupRepository.save(changeOrderGroup))
                .map( newOrderGroup -> response(newOrderGroup))
                .orElseGet(() -> Header.ERROR("변경 하고자 하는 주문 내역 없음 ")) ;

    }

    @Override
    public Header delete(Long id) {

        return orderGroupRepository.findById(id)
                .map( orderGroup -> {
                    orderGroupRepository.delete(orderGroup);
                    return Header.OK();
                })
                .orElseGet(() -> Header.ERROR("삭제 대상이 없습니다다."));
    }

   private Header<OrderGroupApiResponse> response(OrderGroup orderGroup){
         OrderGroupApiResponse body = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .paymentType(orderGroup.getPaymentType())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
         return Header.OK(body);
    }
}
