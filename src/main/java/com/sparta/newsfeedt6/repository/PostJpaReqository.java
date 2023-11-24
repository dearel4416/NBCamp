package com.sparta.newsfeedt6.repository;

import com.sparta.newsfeedt6.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostJpaReqository extends JpaRepository<PostEntity, Long> {
}