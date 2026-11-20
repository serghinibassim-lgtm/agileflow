package com.bassim.agilflowplatforme.web;


import com.bassim.agilflowplatforme.dto.LoginRequest;
import com.bassim.agilflowplatforme.services.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;


    @Value("${app.jwt.expiration}")
    private long jwtExpiration;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request,
            HttpServletResponse response
    ) {

        String token = authService.login(request);  


        ResponseCookie cookie = ResponseCookie.from("ACCESS_TOKEN", token)
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofMillis(jwtExpiration))
                .sameSite("Lax")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        return ResponseEntity.ok(Map.of(
                "message", "Connexion réussie",
                "token", token
        ));

    }

    @PostMapping("/logout")
    public  ResponseEntity<Void> Logout (HttpServletResponse response){

        ResponseCookie responseCookie=ResponseCookie.from("ACCESS_TOKEN","")
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(0)
                .sameSite("Lax")
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE,responseCookie.toString());
        return ResponseEntity.noContent().build();



    }
}
