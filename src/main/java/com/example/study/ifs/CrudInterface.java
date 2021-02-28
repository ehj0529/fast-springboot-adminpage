package com.example.study.ifs;

import com.example.study.model.network.Header;

public interface CrudInterface<Req, Res> {

    Header<Res> create(Header<Req> request);    // todo request object 추가. 제너릭으로 사용하도록 설정.

    Header<Res> read(Long id);

    Header<Res> update(Header<Req> request);

    Header delete(Long id);
}
