package com.example.security_practice;

import com.example.security_practice.auth.PrincipalDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author : ejum
 * @fileName : HomeContoller
 * @since : 8/18/24
 */
@Controller
public class HomeContoller {

    @GetMapping("")
    public String home(){
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @GetMapping("/login")
    public String signIn(){
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model model, PrincipalDetails principalDetails) {
        if (principalDetails == null || principalDetails.getUserEntity() == null) {
            // Handle the case where PrincipalDetails or userEntity is null
            return "redirect:/login"; // or some error page
        }

        model.addAttribute("username", principalDetails.getUsername());
        model.addAttribute("password", principalDetails.getPassword());
        model.addAttribute("authorities", principalDetails.getAuthorities());
        return "profile";
    }

}
