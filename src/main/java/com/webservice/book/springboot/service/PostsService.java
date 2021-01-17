package com.webservice.book.springboot.service;

import com.webservice.book.springboot.domain.posts.PostRepository;
import com.webservice.book.springboot.domain.posts.Posts;
import com.webservice.book.springboot.web.dto.PostsResponseDto;
import com.webservice.book.springboot.web.dto.PostsSaveRequestDto;
import com.webservice.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts= postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts posts= postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 게시글이 없습니다. id = "+id));
        return new PostsResponseDto(posts);
    }
}
