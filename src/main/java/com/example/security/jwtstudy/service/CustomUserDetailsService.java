package com.example.security.jwtstudy.service;

import com.example.security.jwtstudy.adapter.AccountAdapter;
import com.example.security.jwtstudy.entity.Account;
import com.example.security.jwtstudy.service.repository.AccountRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

public class CustomUserDetailsService implements UserDetailsService {
    private final AccountRepository accountRepository;

    public CustomUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String username) {
        Account account = accountRepository.findOneWithAuthoritiesByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " -> 데이터베이스에서 찾을 수 없습니다."));

        if(!account.isActivated()) throw new RuntimeException(account.getUsername() + " -> 활성화되어 있지 않습니다.");
        return new AccountAdapter(account);
    }
}
