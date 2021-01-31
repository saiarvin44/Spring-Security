package com.spring.security.WebTokens.service;

import com.spring.security.WebTokens.entity.Otp;
import com.spring.security.WebTokens.entity.User;
import com.spring.security.WebTokens.repository.OtpRepository;
import com.spring.security.WebTokens.repository.UserRepository;
import com.spring.security.WebTokens.util.GenerateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UserService {
  @Autowired private PasswordEncoder passwordEncoder;

  @Autowired private UserRepository userRepository;

  @Autowired private OtpRepository otpRepository;

  public void addUser(User user) {
    user.setPassword(passwordEncoder.encode(user.getPassword()));
    userRepository.save(user);
  }

  public void auth(User user) {
    Optional<User> o = userRepository.findUserByUsername(user.getUsername());

    if (o.isPresent()) {
      User u = o.get();
      if (passwordEncoder.matches(user.getPassword(), u.getPassword())) {
        renewOtp(u);
      }
    }
  }

  private void renewOtp(User u) {
    String code = GenerateCodeUtil.generateCode();
    Optional<Otp> userOtp = otpRepository.findOtpByUsername(u.getUsername());
    if (userOtp.isPresent()) {
      Otp otp = userOtp.get();
      otp.setCode(code);
    } else {
      Otp otp = new Otp();
      otp.setUsername(u.getUsername());
      otp.setCode(code);
      otpRepository.save(otp);
    }
  }

  public boolean check(Otp otpToValidate) {
    Optional<Otp> userOtp = otpRepository.findOtpByUsername(otpToValidate.getUsername());
    if (userOtp.isPresent()) {
      Otp otp = userOtp.get();
      if (otpToValidate.getCode().equals(otp.getCode())) return true;
    }
    return false;
  }
}
