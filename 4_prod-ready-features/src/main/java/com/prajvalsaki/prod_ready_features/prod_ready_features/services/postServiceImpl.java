package com.prajvalsaki.prod_ready_features.prod_ready_features.services;

import com.prajvalsaki.prod_ready_features.prod_ready_features.dto.PostDTO;
import com.prajvalsaki.prod_ready_features.prod_ready_features.entities.PostEntity;
import com.prajvalsaki.prod_ready_features.prod_ready_features.exceptions.ResourceNotFoundException;
import com.prajvalsaki.prod_ready_features.prod_ready_features.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;



@Service @RequiredArgsConstructor
public class postServiceImpl implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public PostDTO getPostById(Long postId) {
        PostEntity postEntity = postRepository
                .findById(postId)
                .orElseThrow(()-> new ResourceNotFoundException("post not found witj id"+postId));
        return modelMapper.map(postEntity, PostDTO.class);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity, PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createPost(PostDTO inputPost) {
        PostEntity postEntity = modelMapper.map(inputPost, PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity), PostDTO.class);
    }


}
