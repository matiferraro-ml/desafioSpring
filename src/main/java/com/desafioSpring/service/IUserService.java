package com.desafioSpring.service;

import com.desafioSpring.dto.*;
import com.desafioSpring.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;

public interface IUserService {

    UserDTO getUser(int userId);

    ResponseEntity<Object> follow(int userId, int userIdToFollow) throws UserNotFoundException;

    UserFollowersCountDTO followersCount(int userId);

    UserFollowersDTO listOfFollowers(int userId);

    UserFollowersDTO listOfSellersFollowed(int userId);

    PostSellerFollowedDTO listPostsSellersFollowed(int userId);

    ResponseEntity<Object> unfollow(int userId, int userIdToUnfollow);

}
