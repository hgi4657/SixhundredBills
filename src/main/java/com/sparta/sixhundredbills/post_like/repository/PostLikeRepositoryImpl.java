package com.sparta.sixhundredbills.post_like.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.sixhundredbills.post.entity.Post;
import com.sparta.sixhundredbills.post.entity.QPost;
import com.sparta.sixhundredbills.post_like.entity.QPostLike;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class PostLikeRepositoryImpl implements PostLikeRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<Post> findLikePostsByUserId(Long userId, Pageable pageable) {
        QPost post = QPost.post;
        QPostLike postLike = QPostLike.postLike;

        List<Post> posts = jpaQueryFactory
                .select(post)
                .from(postLike)
                .join(postLike.post, post)
                .where(postLike.user.id.eq(userId))
                .orderBy(post.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long postSize = jpaQueryFactory
                .select(post.count())
                .from(postLike)
                .where(postLike.user.id.eq(userId))
                .fetchOne();

        return new PageImpl<>(posts, pageable, postSize);
    }

    @Override
    public Long countByUserId(Long userId) {
        QPostLike postLike = QPostLike.postLike;
        return jpaQueryFactory
                .select(postLike.count())
                .from(postLike)
                .where(postLike.user.id.eq(userId))
                .fetchOne();
    }
}
