package com.example.security_practice.user.repository;

import com.example.security_practice.auth.PrincipalDetails;
import com.example.security_practice.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : ejum
 * @fileName : UserRepository
 * @since : 8/18/24
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

     UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

    boolean existsByEmail(String email);

}
