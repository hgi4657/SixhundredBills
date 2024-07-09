package com.sparta.sixhundredbills.post.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowingPostSearchCond {

    private Long followingUserId;

    @Builder.Default
    private SortType sortType = SortType.CREATE_AT_DESC; // 정렬 타입 (기본값: 생성일자 내림차순)

    public enum SortType {
        CREATE_AT_DESC, // 생성일자 내림차순
        USER_NAME_ASC, // 유저 이름 오름차순
    }
}
