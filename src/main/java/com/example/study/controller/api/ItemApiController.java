package com.example.study.controller.api;

import com.example.study.controller.CrudController;
import com.example.study.ifs.CrudInterface;
import com.example.study.model.network.Header;
import com.example.study.model.network.request.ItemApiRequest;
import com.example.study.model.network.response.ItemApiResponse;
import com.example.study.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/api/item")
public class ItemApiController //implements CrudInterface<ItemApiRequest, ItemApiResponse> { // interface 상속을 제거
         extends CrudController<ItemApiRequest, ItemApiResponse>{  // 인터페이스 대신 추상화 클래스 상속으로 아래의 메소드를 삭제 할수 있음.

    @Autowired
    private ItemApiLogicService itemApiLogicService;

    // 추상화 클래스의 baseService 에 대한 정의가 필요함
    @PostConstruct
    public void init(){
        this.baseService = itemApiLogicService; //
    }
/*
     동일한 메소드가 사용됨으로 관련 된 내용을 abstract class 로 대체 하면 아래의 메소드를 생략 할수 있다.
    @Override
    @PostMapping("") // /api/item
    public Header<ItemApiResponse> create(@RequestBody  Header<ItemApiRequest> request) {
        return itemApiLogicService.create(request);
    }

    @Override
    @GetMapping({"{id}"}) // /api/item/1....100
    public Header<ItemApiResponse> read(@PathVariable Long id) {
        return itemApiLogicService.read(id);
    }

    @Override
    @PutMapping("") // /api/item
    public Header<ItemApiResponse> update(@RequestBody  Header<ItemApiRequest> request) {
        return itemApiLogicService.update(request);
    }

    @Override
    @DeleteMapping("{id}") // /api/item/1....100
    public Header delete(@PathVariable Long id) {
        return itemApiLogicService.delete(id);
    }

 */
}
