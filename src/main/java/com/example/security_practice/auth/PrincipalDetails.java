package com.example.security_practice.auth;

import com.example.security_practice.user.entity.UserEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author : ejum
 * @fileName : PrincipalDetails
 * @since : 8/18/24
 */
@Getter
public class PrincipalDetails  implements UserDetails {

    private final UserEntity userEntity;

    public PrincipalDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(userEntity.getRole()));
        return authorities;
    }

    @Override
        public String getPassword() {
            return userEntity.getPassword();
        }

        @Override
        public String getUsername() {
            return userEntity.getUsername();
        }

    // 계정 비밀번호 기간
        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        // 계정 활성화 되어있니?
        @Override
        public boolean isEnabled() {
            return true;
        }
    }
