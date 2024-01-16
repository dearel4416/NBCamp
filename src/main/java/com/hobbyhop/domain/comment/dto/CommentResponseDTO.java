package com.hobbyhop.domain.comment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CommentResponseDTO {
    String content;
    String writer;
    int like;
    Timestamp createdAt;
    List<CommentResponseDTO> reply;
}
