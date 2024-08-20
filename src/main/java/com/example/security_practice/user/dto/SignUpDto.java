package com.example.security_practice.user.dto;

import lombok.Data;
import lombok.Getter;

/**
 * @author : ejum
 * @fileName : SignUpDto
 * @since : 8/18/24
 */
@Data
public class SignUpDto {
    private String username;
    private String password;
    private String email;
    private int age;
    private String role; // 필요에 따라 기본값을 설정하거나, 엔티티에서 설정해도 됩니다.

}
