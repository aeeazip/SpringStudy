package org.example.controller.posts;

import org.example.domain.posts.Posts;
import org.example.domain.posts.PostsRepository;
import org.example.dto.PostsSaveRequestDto;
import org.example.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsControllerTest {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void uploadPostTest() throws Exception {
        String title = "title";
        String content = "content";

        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:" + port + "/post/upload";

        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> list = postsRepository.findAll();
        Posts post = list.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }

    @Test
    public void updatePostTest() throws Exception {
        // DB에 데이터 넣기
        Posts savedPost = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        // 변경하고 싶은 id, title, content 지정
        Long updatedId = savedPost.getId();
        String expectedTitle = "test";
        String expectedContent = "test";

        // 변경할 Post 정보 담은 PostsUpdateRequestDto 객체 생성
        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                        .title(expectedTitle)
                        .content(expectedContent)
                        .build();

        // 요청 URL
        String url = "http://localhost:" + port + "/post/" + updatedId;
        // HttpEntity : 헤더와 바디로 이루어진 Http request, response 엔티티 (요청 보낼 request 정보 담고 있음)
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when (requestEntity id와 일치하는 레코드 찾고 exchange 메소드 써서 title, content를 update 한다!!)
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> list = postsRepository.findAll();
        assertThat(list.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(list.get(0).getContent()).isEqualTo(expectedContent);
    }
}
