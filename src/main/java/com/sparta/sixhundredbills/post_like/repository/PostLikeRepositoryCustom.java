package com.sparta.sixhundredbills.post_like.repository;

import com.sparta.sixhundredbills.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostLikeRepositoryCustom {
    Page<Post> findLikePostsByUserId(Long id, Pageable pageable);
    Long countByUserId(Long id);
}
