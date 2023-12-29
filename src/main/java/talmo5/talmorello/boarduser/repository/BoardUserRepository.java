package talmo5.talmorello.boarduser.repository;

import static talmo5.talmorello.boarduser.entity.QBoardUser.boardUser;
import static talmo5.talmorello.user.entity.QUser.user;

import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import talmo5.talmorello.user.entity.User;

@Repository
@RequiredArgsConstructor
public class BoardUserRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public boolean existBoardUserByUserId(Long boardId, Long userId) {
        return jpaQueryFactory
                .from(boardUser)
                .where(boardUser.boardUserPK.board.id.eq(boardId)
                        .and(boardUser.boardUserPK.user.id.eq(userId)))
                .fetchFirst() != null;
    }

    public List<User> findUserByBoardId(Long boardId) {
        return jpaQueryFactory
                .select(user)
                .from(user)
                .where(user.id.in(
                        JPAExpressions
                                .select(boardUser.boardUserPK.user.id)
                                .from(boardUser)
                                .where(boardUser.boardUserPK.board.id.eq(boardId))
                )).fetch();
    }


}
