package org.example.controller.posts;

import lombok.RequiredArgsConstructor;
import org.example.dto.PostsSaveRequestDto;
import org.example.service.posts.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Autowired 보다 생성자를 사용한 DI 권장
// @RequiredArgsConstructor : final이 붙은 필드를 인자값으로 생성자를 생성
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/post")
public class PostsController {
    private final PostsService postsService;

    @PostMapping("/upload")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }
}
