package com.webservice.book.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest  //실행할 경우 자동으로 H2가 자동으로 실행됨
public class PostsRepositoryTest{

    @Autowired
    PostRepository postsRepository;

    @After //Junit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
           //보통 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기위해 사용
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void getBoard(){
        String title ="테스트 게시글 제목";
        String content = "테스트 본문";

        //postsRepository.save()
        //테이블 posts에 insert/update 쿼리를 실행
        //id값이 있다면 update가 없다면 insert 퀄가 실행
        postsRepository.save(Posts.builder().title(title).content(content).author("yuyu@gmail.com").build());

        List<Posts> postsList = postsRepository.findAll(); // posts 테이블의 모든 데이터 조회

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록 () {
        //given
        LocalDateTime now = LocalDateTime.now();
        postsRepository.save(Posts.builder()
                .title("테스트 게시글")
                .content("테스트 본문")
                .author("jojoldu@gmail.com")
                .build());
        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertTrue(posts.getCreatedDate().isAfter(now));
        assertTrue(posts.getModifiedDate().isAfter(now));
    }
}
