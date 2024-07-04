package com.sparta.sixhundredbills.comment_like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.sixhundredbills.comment_like.entity.QCommentLike;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CommentLikeRepositoryImpl implements CommentLikeRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long countByCommentId(Long commentId) {
        QCommentLike commentlike = QCommentLike.commentLike;

        return queryFactory
                .select(commentlike.count())
                .from(commentlike)
                .where(commentlike.comment.id.eq(commentId))
                .fetchOne();
    }
}
