package com.application.ims.controller;

import com.application.ims.domain.dto.request.create.LoginRequestDto;
import com.application.ims.domain.dto.request.create.RegisterRequestDto;
import com.application.ims.domain.dto.response.LoginResponseDto;
import com.application.ims.domain.entity.Login;
import com.application.ims.infra.security.TokenService;
import com.application.ims.infrastructure.LoginRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final LoginRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {

        Login login = this.repository.findByEmail(loginRequestDto.email()).orElseThrow(() -> new RuntimeException("User not found"));
        if(passwordEncoder.matches(loginRequestDto.password(), login.getPassword())) {
            String token = this.tokenService.generateToken(login);
            return ResponseEntity.ok(new LoginResponseDto(login.getUsername(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {

        Optional<Login> login = this.repository.findByEmail(registerRequestDto.email());

        if(login.isEmpty()) {
            Login newLogin = new Login();

            newLogin.setPassword(passwordEncoder.encode(registerRequestDto.password()));
            newLogin.setEmail(registerRequestDto.email());
            newLogin.setUsername(registerRequestDto.name());
            this.repository.save(newLogin);

            String token = this.tokenService.generateToken(newLogin);
            return ResponseEntity.ok(new LoginResponseDto(newLogin.getUsername(), token));

        }
        return ResponseEntity.badRequest().build();
    }

}
