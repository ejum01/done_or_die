package com.example.security_practice.user.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

/**
 * @author : ejum
 * @fileName : UserDto
 * @since : 8/18/24
 */
@Getter
@Setter
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private int age;
    private String role;
    private LocalDateTime createDate;
}
