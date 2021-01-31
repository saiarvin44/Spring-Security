package com.spring.security.WebTokens.repository;

import com.spring.security.WebTokens.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
  Optional<Otp> findOtpByUsername(String username);
}
