package com.sparta.sixhundredbills.follow.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Embeddable
public class FollowId implements Serializable {
    @Serial
    private static final long serialVersionUID = 442813812396136126L;

    @Column(name = "following_user_id")
    private Long followingUserId; // 팔로윙 하는 유저 (사용자가 추가한 사람)

    @Column(name = "followed_user_id")
    private Long followedUserId;  // 팔로워 하는 유저 (사용자를 추가한 사람)

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        FollowId followId = (FollowId) o;
        return Objects.equals(getFollowingUserId(), followId.getFollowingUserId())
                && Objects.equals(getFollowedUserId(), followId.getFollowedUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFollowingUserId(), getFollowedUserId());
    }
}
