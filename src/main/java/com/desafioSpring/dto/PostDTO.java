package com.desafioSpring.dto;

import com.desafioSpring.entity.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    @JsonIgnore                 // Agrego la anotacion @JsonIgnore para que no retorne el userId
    private int userId;
    private int postId;
    private Date date;
    private Product detail;
    private int category;
    private Double price;

}
