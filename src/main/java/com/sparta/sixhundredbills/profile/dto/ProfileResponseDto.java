package com.sparta.sixhundredbills.profile.dto;

import com.sparta.sixhundredbills.auth.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProfileResponseDto {  // 응답
    private String email;
    private String name;
    private Long myLikePostsCount;
    private Long myLikeCommentsCount;

    // User Entity 를 받아서 필요한 값만 응답하도록 설정
    public static ProfileResponseDto fromUser(User user) {
        return ProfileResponseDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .build();
    }

    public ProfileResponseDto(User user, Long likePostsCount, Long likeCommentsCount) {
        this.email = user.getEmail();
        this.name = user.getName();
        this.myLikePostsCount = likePostsCount;
        this.myLikeCommentsCount = likeCommentsCount;
    }

}
