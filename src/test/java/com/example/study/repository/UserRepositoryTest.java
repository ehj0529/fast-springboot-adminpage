package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Item;
import com.example.study.model.entity.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;


public class UserRepositoryTest extends StudyApplicationTests {

    @Autowired  //Dependency Injection
    private UserRepository userRepository;

    @Test
    public void create(){

        String account = "Test04";
        String password = "Test04";
        String status = "REGISTERED";
        String email = "Test04@gmail.com";
        String phoneNumber = "010-1111-3456";
        LocalDateTime registeredAt =  LocalDateTime.now();
        LocalDateTime createdAt =  LocalDateTime.now();
        String createdBy = "AdminServer";

        User user = new User();
        user.setAccount(account);
        user.setPassword(password);
        user.setStatus(status);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setRegisteredAt(registeredAt);

        User u = User.builder()
                .account(account)
                .password(password)
                .status(status)
                .email(email)
                .build();


        User newUser = userRepository.save(user);

        Assert.assertNotNull(newUser);
        Assert.assertEquals(newUser.getAccount(), account);
        Assert.assertEquals(newUser.getStatus(), status);
        Assert.assertEquals(newUser.getEmail(), email);
        Assert.assertEquals(newUser.getPhoneNumber(), phoneNumber);

    }

    @Test
    @Transactional
    public void read(){

        User user = userRepository.findFirstByPhoneNumberOrderByIdDesc("010-1111-1234");

        user.getOrderGroupList().stream().forEach(orderGroup -> {
            System.out.println("-----------------주문묶음------------------");
            System.out.println("수령인:"+orderGroup.getRevName());
            System.out.println("수령지:"+orderGroup.getRevAddress());
            System.out.println("총금액:"+orderGroup.getTotalPrice());
            System.out.println("총수량:"+orderGroup.getTotalQuantity());
            System.out.println("-----------------주문상세------------------");

            orderGroup.getOrderDetailList().forEach( orderDetail -> {
                System.out.println("파트너사 이름 "+ orderDetail.getItem().getPartner().getName());
                System.out.println("파트너사 카테고리 "+ orderDetail.getItem().getPartner().getCategory().getTitle());
                System.out.println("주문 상품:"+ orderDetail.getItem().getName());
                System.out.println("고센 전번:"+ orderDetail.getItem().getPartner().getCallCenter());
                System.out.println("주문 상태:"+ orderDetail.getStatus());
                System.out.println("도착예정일:"+ orderDetail.getArrivalDate());
            });
        });

        Assert.assertNotNull(user);


    }

    @Test
    public void update(){

        Optional<User> user = userRepository.findById(2L);

        user.ifPresent( selectUser ->{
            selectUser.setAccount("pppp");
            selectUser.setUpdatedAt(LocalDateTime.now());
            selectUser.setUpdatedBy("update Method()");

            userRepository.save(selectUser);
        });

    }

    @Test
    @Transactional  // 실행을 되는데 롤백 처리가 된다.
    public void delete(){
        Optional<User> user = userRepository.findById(1L);

        Assert.assertTrue(user.isPresent()); //데이터가 존재 하여야한다. 체크

        user.ifPresent( selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

        Assert.assertFalse(deleteUser.isPresent()); //데이터가 없어야 된다. 체크

    }

}