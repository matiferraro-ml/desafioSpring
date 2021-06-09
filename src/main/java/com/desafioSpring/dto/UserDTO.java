package com.desafioSpring.dto;

import com.desafioSpring.entity.Post;
import com.desafioSpring.entity.UserFollow;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private int userId;
    private String userName;
    private List<UserFollow> followers;
    private List<UserFollow> followeds;
    private List<Post> listPost;

}
