package com.example.study.component;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoginUserAuditorAware implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // return Optional.empty(); 현재 로그인 시스템이 없음으로 하드코딩을 해놓는다.
        return Optional.of("AdminServer");
    }
}
