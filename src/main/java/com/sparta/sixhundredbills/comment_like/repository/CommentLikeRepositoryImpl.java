package com.sparta.sixhundredbills.comment_like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.sixhundredbills.comment.entity.Comment;
import com.sparta.sixhundredbills.comment.entity.QComment;
import com.sparta.sixhundredbills.comment_like.entity.QCommentLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;


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

    @Override
    public Page<Comment> findLikeCommentsByUserId(Long userId, Pageable pageable) {
        QComment comment = QComment.comment1;
        QCommentLike commentLike = QCommentLike.commentLike;

        List<Comment> comments = queryFactory
                .select(comment)
                .from(commentLike)
                .join(commentLike.comment, comment)
                .where(commentLike.user.id.eq(userId))
                .orderBy(comment.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long commentSize = queryFactory
                .select(comment.count())
                .from(commentLike)
                .where(commentLike.user.id.eq(userId))
                .fetchOne();

//        return new PageImpl<>(comments, pageable, commentSize);
        return PageableExecutionUtils.getPage(comments, pageable, () -> commentSize);
    }

    @Override
    public Long countByUserId(Long userId) {
        QCommentLike commentLike = QCommentLike.commentLike;
        return queryFactory
                .select(commentLike.count())
                .from(commentLike)
                .where(commentLike.user.id.eq(userId))
                .fetchOne();
    }
}
