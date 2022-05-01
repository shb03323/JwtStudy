package com.example.security.jwtstudy.controller;

import com.example.security.jwtstudy.service.UserService;
import com.example.security.jwtstudy.service.dto.RequestUserRegisterDTO;
import com.example.security.jwtstudy.service.dto.ResponseUserRegisterDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // user 등록 API
    @PostMapping("/user/signup")
    public ResponseEntity<ResponseUserRegisterDTO> signup(
            @Valid @RequestBody RequestUserRegisterDTO registerDto
    ) {
        ResponseUserRegisterDTO userInfo = userService.signup(registerDto);

        return ResponseEntity.ok(userInfo);
    }
}
