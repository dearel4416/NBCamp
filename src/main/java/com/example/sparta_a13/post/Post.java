package com.example.sparta_a13.post;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Post {

  @Id @Column(name = "postId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

}
