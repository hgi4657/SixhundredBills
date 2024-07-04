package com.sparta.sixhundredbills.comment.dto;

import com.sparta.sixhundredbills.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentResponseDto {
    private String showName; // 익명으로 표시될 이름
    private String comment;

    public CommentResponseDto(Comment comment) {
        this.showName = comment.getShowName();
        this.comment = comment.getComment();
    }
}