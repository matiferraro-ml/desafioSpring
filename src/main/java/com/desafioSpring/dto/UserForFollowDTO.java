package com.desafioSpring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForFollowDTO {

    private int userIdFrom;
    private int userIdTo;

}
