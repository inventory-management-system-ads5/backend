package com.application.ims.controller;

import com.application.ims.domain.dto.request.create.LoginRequestDto;
import com.application.ims.domain.dto.request.create.RegisterRequestDto;
import com.application.ims.domain.dto.response.LoginResponseDto;
import com.application.ims.domain.entity.User;
import com.application.ims.infra.security.TokenService;
import com.application.ims.infrastructure.UserRepository;
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
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto) {
        User user = this.repository.findByEmail(loginRequestDto.email()).orElseThrow(() -> new RuntimeException("User Not Found"));

        if (passwordEncoder.matches(loginRequestDto.password(), user.getPassword())) {
            String token =  this.tokenService.generateToken(user);
            return ResponseEntity.ok(new LoginResponseDto(user.getFullName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegisterRequestDto registerRequestDto) {
        Optional<User> user = this.repository.findByEmail(registerRequestDto.email());

        if(user.isEmpty()) {
            User newUser = new User();

            newUser.setPassword(passwordEncoder.encode(registerRequestDto.password()));
            newUser.setEmail(registerRequestDto.email());
            newUser.setFullName(registerRequestDto.name());

            this.repository.save(newUser);

            String token = this.tokenService.generateToken(newUser);
            return ResponseEntity.ok(new LoginResponseDto(newUser.getFullName(), token));
        }
        return ResponseEntity.badRequest().build();
    }

}
