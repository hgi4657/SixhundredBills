package com.sparta.sixhundredbills.post.controller;

import com.sparta.sixhundredbills.auth.security.UserDetailsImpl;
import com.sparta.sixhundredbills.post.dto.PostRequestDto;
import com.sparta.sixhundredbills.post.dto.PostResponseDto;
import com.sparta.sixhundredbills.post.entity.Post;
import com.sparta.sixhundredbills.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시물 생성
     * @param postRequestDto 생성할 게시물의 정보
     * @param userDetails 인증된 사용자 정보
     * @return 생성된 게시물의 응답 데이터
     */

    @PostMapping
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.createPost(postRequestDto, userDetails.getUser());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    /**
     * 게시물 조회 (페이지네이션)
     * @param page 요청한 페이지 번호
     * @return 페이지네이션된 게시물의 응답 데이터
     */
    @GetMapping
    public ResponseEntity<Page<PostResponseDto>> getPosts(@RequestParam int page) {
        int size = 5;
        Page<PostResponseDto> posts = postService.getPosts(page, size);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    /**
     * 게시물 단건 조회
     * @param postId 요청한 게시물 ID
     * @return 게시물 응답 데이터
     */
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long postId) {
        PostResponseDto responseDto = postService.getPostById(postId);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 사용자가 좋아요한 게시글 조회
     * @param userDetails 인증된 유저
     * @param pageable 페이지네이션
     * @return 페이지네이션된 게시물 응답 데이터
     */
    @GetMapping("/myLikes")
    public ResponseEntity<Page<PostResponseDto>> getMyLikePosts(@AuthenticationPrincipal UserDetailsImpl userDetails, Pageable pageable) {
        Page<Post> likePosts = postService.getLikePostsByUser(userDetails.getUser(), pageable);
        Page<PostResponseDto> responseDtos = likePosts.map(PostResponseDto::new);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    /**
     * 게시물 수정
     * @param postId 수정할 게시물의 ID
     * @param postRequestDto 수정할 게시물의 정보
     * @param userDetails 인증된 사용자 정보
     * @return 수정된 게시물의 응답 데이터
     */
    @PutMapping("/{postId}")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long postId, @RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        PostResponseDto responseDto = postService.updatePost(postId, postRequestDto, userDetails.getUser());
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    /**
     * 게시물 삭제
     * @param postId 삭제할 게시물의 ID
     * @param userDetails 인증된 사용자 정보
     * @return HTTP 상태 코드
     */
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        postService.deletePost(postId, userDetails.getUser());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}