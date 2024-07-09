package com.sparta.sixhundredbills.follow.repository;

import com.sparta.sixhundredbills.follow.entity.Follow;
import com.sparta.sixhundredbills.follow.entity.FollowId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FollowRepository extends JpaRepository<Follow, FollowId> {

}
