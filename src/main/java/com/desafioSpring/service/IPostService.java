package com.desafioSpring.service;

import com.desafioSpring.dto.PostDTO;
import org.springframework.http.ResponseEntity;

public interface IPostService {

    ResponseEntity<Object> postProduct(PostDTO postDTO);

}
