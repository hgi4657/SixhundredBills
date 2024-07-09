package com.sparta.sixhundredbills.follow.service;

import com.sparta.sixhundredbills.auth.entity.User;
import com.sparta.sixhundredbills.follow.entity.Follow;
import com.sparta.sixhundredbills.follow.entity.FollowId;
import com.sparta.sixhundredbills.follow.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    /**
     * 팔로우 기능
     * @param followingUser
     * @param followedUser
     */
    public void follow(User followingUser, User followedUser) {
        if(followRepository.existsById(new FollowId(followingUser.getId(), followedUser.getId()))) {
            throw new IllegalArgumentException("이미 팔로우 중입니다.");
        } else if (followingUser.equals(followedUser)) {
            throw new IllegalArgumentException("자기 자신은 팔로우 할 수 없습니다.");
        }
        followRepository.save(new Follow(followingUser, followedUser));
    }

    /**
     * 언팔로우 기능
     * @param followingUser
     * @param followedUser
     */
    public void unfollow(User followingUser, User followedUser) {
        if(!followRepository.existsById(new FollowId(followingUser.getId(), followedUser.getId()))) {
            throw new IllegalArgumentException("팔로우 중이 아닙니다");
        }
        followRepository.deleteById(new FollowId(followingUser.getId(), followedUser.getId()));
    }

    /**
     * 팔로우 여부
     * @param followingUser
     * @param followedUser
     * @return
     */
    public boolean isFollowing(User followingUser, User followedUser) {
        return followRepository.existsById(new FollowId(followingUser.getId(), followedUser.getId()));
    }
}
