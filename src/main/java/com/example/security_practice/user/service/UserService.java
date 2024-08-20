package com.example.security_practice.user.service;

import com.example.security_practice.user.dto.SignUpDto;
import com.example.security_practice.user.entity.UserEntity;
import com.example.security_practice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author : ejum
 * @fileName : UserService
 * @since : 8/18/24
 */
@Service
public class UserService  {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserEntity save(SignUpDto signUpDto) {

        if (userRepository.existsByEmail(signUpDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpDto.getPassword());

        // UserEntity 생성
        UserEntity user = UserEntity.builder()
                .username(signUpDto.getUsername())
                .password(encodedPassword)
                .email(signUpDto.getEmail())
                .age(signUpDto.getAge())
                .role(signUpDto.getRole() != null ? signUpDto.getRole() : "ROLE_USER")
                .build();

        return userRepository.save(user);
    }
}
