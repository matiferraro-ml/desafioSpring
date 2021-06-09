package com.desafioSpring.service.impl;

import com.desafioSpring.dto.PostDTO;
import com.desafioSpring.entity.Post;
import com.desafioSpring.repository.IUserRepository;
import com.desafioSpring.service.IPostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public ResponseEntity<Object> postProduct(PostDTO postDTO) {
        postDTO.setPostId(UUID.randomUUID().variant());             // TODO: Refactor
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        postDTO.setDate(Calendar.getInstance().getTime());          // TODO: Take only de date, not de time
        ModelMapper modelMapper = new ModelMapper();
        Post post = modelMapper.map(postDTO, Post.class);
        userRepository.postProduct(post);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
