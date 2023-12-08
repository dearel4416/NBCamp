package com.example.sparta_a13.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {

  private String username;

  public UserDTO(String username) {
    this.username = username;
  }
}
