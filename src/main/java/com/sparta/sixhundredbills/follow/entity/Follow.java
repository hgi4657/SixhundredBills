package com.sparta.sixhundredbills.follow.entity;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.timestamp.TimeStamp;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Follow extends TimeStamp {
    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
    @EmbeddedId // 두개의 키값을 사용해서 복합키로 테이블을 구성하고 싶을 때 사용
    private FollowId followId = new FollowId();

    /**
     * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
     */
    public Follow(User followingUser, User followedUser) {
        this.followId = new FollowId(followingUser.getId(), followedUser.getId());
        this.followingUser = followingUser;
        this.followedUser = followedUser;
    }

    /**
     * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
     */
    @ManyToOne
    @MapsId("followingUserId") // 어떤 아이디와 매핑되는지 설정
    @JoinColumn(name = "following_user_id") // 조인하려는 컬럼 설정
    private User followingUser;

    @ManyToOne
    @MapsId("followedUserId")
    @JoinColumn(name = "followed_user_id")
    private User followedUser;

    /**
     * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
     */


    /**
     * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
     */
}
