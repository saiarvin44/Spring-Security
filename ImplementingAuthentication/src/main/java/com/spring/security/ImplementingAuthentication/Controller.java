package com.spring.security.ImplementingAuthentication;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("")
public class Controller {

  @GetMapping("/hello")
  public String hello() {
    SecurityContext context = SecurityContextHolder.getContext();
    Authentication a = context.getAuthentication();
    return "Hello," + a.getName() + "!";
  }

  @GetMapping("/bye")
  @Async
  public String goodbye() {
    SecurityContext context = SecurityContextHolder.getContext();
    String username = context.getAuthentication().getName();
    return "Hello," + username + "!";
  }

  @GetMapping("/ciao")
  public String ciao() throws Exception {
    Callable<String> task =
        () -> {
          SecurityContext context = SecurityContextHolder.getContext();
          return context.getAuthentication().getName();
        };
    ExecutorService e = Executors.newCachedThreadPool();
    e = new DelegatingSecurityContextExecutorService(e);
    try {
      return "Ciao, " + e.submit(task).get() + "!";
    } finally {
      e.shutdown();
    }
  }
}
