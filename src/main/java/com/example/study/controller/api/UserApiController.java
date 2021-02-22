package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface {


    @Override
    @PostMapping("")  // 값을 넣지 않으면 /api/user 로 매핑.
    public Header create() {
        return null;
    }

    @Override
    @GetMapping("{id}")  // /api/user/{id} 로 호출된다.
    public Header read(@PathVariable(name = "id") Long id) {
        return null;
    }

    @Override
    @PutMapping("") // /api/user 로 호출
    public Header update() {
        return null;
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        return null;
    }
}
