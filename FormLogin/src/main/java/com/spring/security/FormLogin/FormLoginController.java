package com.spring.security.FormLogin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FormLoginController {
  @GetMapping("/home")
  public String home() {
    return "home.html";
  }
}
