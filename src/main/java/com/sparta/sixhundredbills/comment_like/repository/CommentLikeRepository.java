package com.sparta.sixhundredbills.comment_like.repository;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.comment.entity.Comment;
import com.sparta.sixhundredbills.comment_like.entity.CommentLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 댓글 좋아요 레포지토리 인터페이스
 */

public interface CommentLikeRepository extends JpaRepository<CommentLike, Long>, CommentLikeRepositoryCustom {
    // 사용자와 댓글로 좋아요 찾기
    Optional<CommentLike> findByUserAndComment(User user, Comment comment);
}