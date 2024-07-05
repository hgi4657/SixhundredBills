package com.sparta.sixhundredbills.comment_like.repository;

import com.sparta.sixhundredbills.comment.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentLikeRepositoryCustom {
    Long countByCommentId(Long commentId);
    Page<Comment> findLikeCommentsByUserId(Long userId, Pageable pageable);
    Long countByUserId(Long userId);
}
