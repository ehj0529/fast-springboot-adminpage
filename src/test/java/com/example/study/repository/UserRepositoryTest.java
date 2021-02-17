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
        User user = new User();
        user.setAccount("TestUser03");
        user.setEmail("TestUser03@gmail.com");
        user.setPhoneNumber("010-1111-3333");
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("TestUser3");

        System.out.printf(" User : %s%n", user);

        User newUser = userRepository.save(user);

        System.out.printf("new User : %s%n", newUser);

    }

    @Test
    @Transactional
    public void read(){
        //Optional<User> user = userRepository.findById(7L);  //PK설정으로 자동으로 생성됨.
        Optional<User> user = userRepository.findByAccount("TestUser03"); //사용자가 특정 컬럼을 조회 할수있도록 정의 함.
        user.ifPresent( selectUser ->{
            // JPA에서 N:1의 설정에서 1전체를  호출 하게 되면 StackOverflowError 가 발생됨. lombok에서 toString 자동설정으로 발생.
            // System.out.println("user : " + selectUser);
            // System.out.println("eMail : " + selectUser.getEmail());

            //selectUser.getOrderDetailList().stream().forEach( detail ->{
                //Item item = detail.getItem();
                //System.out.println(item);
                //System.out.println("orderDetail Item_ID :: "+detail.getItemId());
                //System.out.println("잘됨.");
            //});

        });

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
        Optional<User> user = userRepository.findById(3L);

        Assert.assertTrue(user.isPresent()); //데이터가 존재 하여야한다. 체크

        user.ifPresent( selectUser ->{
            userRepository.delete(selectUser);
        });

        Optional<User> deleteUser = userRepository.findById(3L);

//        if(deleteUser.isPresent()){
//            System.out.println("데이터 존재:"+deleteUser.get());
//        } else {
//            System.out.println("데이터 삭제 완료 데이터 없음");
//        }

        Assert.assertFalse(deleteUser.isPresent()); //데이터가 없어야 된다. 체크

    }

}