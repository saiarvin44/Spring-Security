package com.spring.security.WebTokens.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class User {
  @Id private String username;
  private String password;
}
