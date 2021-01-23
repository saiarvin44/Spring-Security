package com.spring.security.Authenticationfinaldemo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String name;

  private double price;

  @Enumerated(EnumType.STRING)
  private Currency currency;
}
