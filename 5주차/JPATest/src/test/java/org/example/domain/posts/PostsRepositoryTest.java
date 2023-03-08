package org.example.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After  // JUnit 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void findPostTest() {
        String title = "테스트게시글";
        String content = "테스트본문";

        // repository.save() : id값이 있으면 update, 없으면 insert 실행
        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("test@naver.com")
                .build());

        List<Posts> postList = postsRepository.findAll();   // 모든 데이터 조회 메소드

        Posts posts = postList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }
}

