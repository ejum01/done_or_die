package com.example.security_practice.user.controller;

import com.example.security_practice.auth.PrincipalDetails;
import com.example.security_practice.user.dto.SignUpDto;
import com.example.security_practice.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

/**
 * @author : ejum
 * @fileName : UserController
 * @since : 8/18/24
 */
@Controller
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String userHome(@AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
        if (principalDetails == null) {
            // 인증되지 않은 사용자에 대한 처리 (예: 로그인 페이지로 리다이렉트)
            return "redirect:/login";
        }
        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("password", principalDetails.getPassword());
        model.addAttribute("authorities", principalDetails.getAuthorities());
        model.addAttribute("credentialsNonExpired", principalDetails.isCredentialsNonExpired());
        return "profile"; // profile.html 템플릿을 반환
    }

    @GetMapping
    public String userpage(){
        return "";
    }

    @PostMapping
    public String save(SignUpDto signUpDto) {
        userService.save(signUpDto);
        return "redirect:/api/user/profile";  // 프로필 페이지로 리다이렉트
    }
}
