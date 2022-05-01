package com.example.security.jwtstudy.service.dto;

import com.example.security.jwtstudy.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserRegisterDTO {
    private String username;
    private String password;
    private String nickname;
    private Set<String> authoritySet;

    public static ResponseUserRegisterDTO of(Account account) {
        if(account == null) return null;

        return ResponseUserRegisterDTO.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .nickname(account.getNickname())
                .authoritySet(account.getAuthorities().stream()
                        .map(authority -> authority.getAuthorityName())
                        .collect(Collectors.toSet()))
                .build();
    }
}
