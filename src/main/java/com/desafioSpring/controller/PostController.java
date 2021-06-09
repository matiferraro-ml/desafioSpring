package com.desafioSpring.controller;

import com.desafioSpring.dto.PostDTO;
import com.desafioSpring.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

    @Autowired
    IPostService postService;

    @PostMapping("/products/newpost")
    public ResponseEntity<Object> postProduct(@RequestBody PostDTO postDTO) {
        return postService.postProduct(postDTO);
    }

}
