package com.sparta.sixhundredbills.post.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.sixhundredbills.auth.entity.QUser;
import com.sparta.sixhundredbills.follow.entity.QFollow;
import com.sparta.sixhundredbills.post.dto.FollowingPostSearchCond;
import com.sparta.sixhundredbills.post.entity.Post;
import com.sparta.sixhundredbills.post.entity.QPost;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Page<Post> findPostsByFollowingUser(FollowingPostSearchCond cond, Pageable pageable) {
        QPost post = QPost.post;
        QUser user = QUser.user;
        QFollow follow = QFollow.follow;

        JPQLQuery<Post> query = queryFactory
                .selectFrom(post)
                .join(post.user, user)
                .where(user.id.in(
                        JPAExpressions.select(follow.followedUser.id)
                                .from(follow)
                                .where(follow.followedUser.id.eq(cond.getFollowingUserId()))
                ));

        // 정렬 조건 적용
        if (cond.getSortType() == FollowingPostSearchCond.SortType.USER_NAME_ASC) {
            query.orderBy(post.user.name.asc());
        } else {
            query.orderBy(post.createdAt.desc());
        }

        // 페이지네이션 적용
        QueryResults<Post> results = query
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
