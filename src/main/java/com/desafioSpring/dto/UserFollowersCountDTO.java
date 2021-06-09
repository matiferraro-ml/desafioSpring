package com.desafioSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserFollowersCountDTO {

    private int userId;
    private String userName;
    private int followersCount;

}
