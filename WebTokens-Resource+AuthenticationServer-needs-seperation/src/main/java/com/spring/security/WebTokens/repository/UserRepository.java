package com.spring.security.WebTokens.repository;

import com.spring.security.WebTokens.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
  Optional<User> findUserByUsername(String username);
}
