package com.example.sparta_a13.user.repository;

import com.example.sparta_a13.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUserName(String username);

  Optional<User> findByUserId(Long followerId);

}