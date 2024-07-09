package com.sparta.sixhundredbills.post.repository;

import com.sparta.sixhundredbills.post.dto.FollowingPostSearchCond;
import com.sparta.sixhundredbills.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {
    Page<Post> findPostsByFollowingUser(FollowingPostSearchCond cond, Pageable pageable);
}
