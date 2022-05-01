package com.example.security.jwtstudy.service;

import com.example.security.jwtstudy.provider.TokenProvider;
import com.example.security.jwtstudy.service.dto.ResponseLoginDTO;
import com.example.security.jwtstudy.service.repository.AccountRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;

    public AuthService(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder, AccountRepository accountRepository) {
        this.tokenProvider = tokenProvider;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.accountRepository = accountRepository;
    }

    // username 과 패스워드로 사용자를 인증하여 액세스토큰을 반환한다.
    public ResponseLoginDTO authenticate(String username, String password) {
        // 받아온 유저네임과 패스워드를 이용해 UsernamePasswordAuthenticationToken 객체 생성
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);

        // authenticationToken 객체를 통해 Authentication 객체 생성
        // 이 과정에서 CustomUserDetailsService 에서 우리가 재정의한 loadUserByUsername 메서드 호출
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        // 그 객체를 시큐리티 컨텍스트에 저장
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 인증 정보를 기준으로 jwt access 토큰 생성
        String accessToken = tokenProvider.createToken(authentication);

        return ResponseLoginDTO.builder()
                .accessToken(accessToken)
                .build();
    }
}
