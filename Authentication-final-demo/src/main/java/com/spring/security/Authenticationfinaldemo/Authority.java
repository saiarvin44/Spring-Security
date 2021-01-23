package com.spring.security.Authenticationfinaldemo;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Authority {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  @JoinColumn(name = "user")
  @ManyToOne
  private User user;

  // Omitted getters and setters
}
