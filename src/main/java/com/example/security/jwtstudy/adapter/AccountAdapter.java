package com.example.security.jwtstudy.adapter;

import com.example.security.jwtstudy.entity.Account;
import com.example.security.jwtstudy.entity.Authority;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AccountAdapter extends User {
    private Account account;

    public AccountAdapter(Account account) {
        super(account.getUsername(), account.getPassword(), authorities(account.getAuthorities()));
        this.account = account;
    }

    private static List<GrantedAuthority> authorities(Set<Authority> authorities) {
        return authorities.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());
    }
}
