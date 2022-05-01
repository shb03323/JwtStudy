package com.example.security.jwtstudy.service.repository;

import com.example.security.jwtstudy.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
