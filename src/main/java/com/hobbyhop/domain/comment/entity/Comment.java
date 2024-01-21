package com.hobbyhop.domain.comment.entity;

import com.hobbyhop.domain.BaseEntity;
import com.hobbyhop.domain.post.entity.Post;
import com.hobbyhop.domain.user.entity.User;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.DialectOverride.Wheres;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLDeletes;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted_at is NULL")
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String content;

    @ManyToOne
    User user;

    @ManyToOne
    Post post;

    @Column(name="deleted_at")
    private Timestamp deletedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    // 댓글 하나에 여러 개의 리플이 붙음
    @OneToMany(mappedBy = "parent", orphanRemoval = true)
    private List<Comment> reply;

    public void changeContent(String content) {
        this.content = content;
    }
}
