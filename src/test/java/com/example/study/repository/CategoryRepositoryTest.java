package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create(){

        String type = "COMPUTER";
        String title= "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();

        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);
        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);


    }

    @Test
    public void read(){

        String type ="COMPUTER";

        //Optional<Category> optionalCategory = categoryRepository.findById(1L);
        Optional<Category> optionalCategory = categoryRepository.findByType(type); // 쿼리메소드 호출

        //select * from category where title = ? ;

        optionalCategory.ifPresent(category -> {

            Assert.assertEquals(category.getType(), type);
            System.out.println(category.getId());
            System.out.println(category.getTitle());
            System.out.println(category.getType());
        });



    }

}