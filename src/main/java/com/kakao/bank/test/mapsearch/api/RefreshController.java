package com.kakao.bank.test.mapsearch.api;

import com.kakao.bank.test.mapsearch.domain.user.model.User;
import com.kakao.bank.test.mapsearch.security.jwt.JwtInfo;
import com.kakao.bank.test.mapsearch.util.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/refresh")
public class RefreshController {

    @GetMapping
    public ResponseEntity<String> refreshToken(Authentication authentication) {
        User user = new User(authentication.getPrincipal().toString(), new ArrayList<>(authentication.getAuthorities()));

        String token = JwtUtil.refreshToken(user);

        HttpHeaders headers = new HttpHeaders();
        headers.add(JwtInfo.HEADER_NAME, token);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).build();
    }
}