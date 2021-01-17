package com.webservice.book.springboot.web.dto;

import com.webservice.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public  PostsResponseDto(Posts entity){  //Entity의 필드 중 일부만사용
        this.id = id;
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
