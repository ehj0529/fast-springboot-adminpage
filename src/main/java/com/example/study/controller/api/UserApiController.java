package com.example.study.controller.api;

import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.UserApiRequest;
import com.example.study.model.network.response.UserApiResponse;
import com.example.study.service.UserApiLogicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/user")
public class UserApiController implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserApiLogicService userApiLogicService;

    @Override
    @PostMapping("")  // 값을 넣지 않으면 /api/user 로 매핑.
    public Header<UserApiResponse> create(@RequestBody Header<UserApiRequest> request) {
        log.info("{}",request );
        return userApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")  // /api/user/{id} 로 호출된다.
    public Header<UserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("read id : {}",id);
        return userApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/user 로 호출
    public Header<UserApiResponse> update(@RequestBody Header<UserApiRequest> request) {
        return userApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/user/{id}
    public Header delete(@PathVariable(name = "id") Long id) {
        return userApiLogicService.delete(id);
    }
}
