package com.hobbyhop.domain.commentuser.entity;

import com.hobbyhop.domain.comment.entity.Comment;
import com.hobbyhop.domain.commentuser.pk.CommentUserPK;
import com.hobbyhop.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import java.sql.Timestamp;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Getter
@Entity
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE comment_user SET deleted_at = NOW() where comment_id=? and user_id=?")
@Where(clause = "deleted_at is NULL")
public class CommentUser {

    @EmbeddedId
    private CommentUserPK commentUserPK;

    @Column
    private Timestamp deletedAt;

    public static CommentUser buildCommentUser(Comment comment, User user){
        return CommentUser.builder()
                .commentUserPK(CommentUserPK.builder()
                        .user(user)
                        .comment(comment)
                        .build())
                .build();
    }

    public void restore(){
        deletedAt = null;
    }
}
