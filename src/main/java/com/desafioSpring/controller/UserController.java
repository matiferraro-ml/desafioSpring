package com.desafioSpring.controller;

import com.desafioSpring.dto.*;
import com.desafioSpring.exception.UserNotFoundException;
import com.desafioSpring.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/users/{userId}/follow/{userIdToFollow}")
    public ResponseEntity<Object> follow(@PathVariable int userId, @PathVariable int userIdToFollow) throws UserNotFoundException {
        return userService.follow(userId, userIdToFollow);
    }

    @GetMapping("/users/{userId}/followers/count")
    public UserFollowersCountDTO getFollowersCount(@PathVariable int userId) {
        return userService.followersCount(userId);
    }

    @GetMapping("/users/{userId}/followers/list")
    public UserFollowersDTO getListOfFollowers(@PathVariable int userId) {
        return userService.listOfFollowers(userId);
    }

    @GetMapping("/users/{userId}/followed/list")
    public UserFollowersDTO getListOfSellersFollowed(@PathVariable int userId) {
        return userService.listOfSellersFollowed(userId);
    }

    @GetMapping("/products/followed/{userId}/list")
    public PostSellerFollowedDTO getPostSellerFollowed(@PathVariable int userId) {
        return userService.listPostsSellersFollowed(userId);
    }

    @PostMapping("/users/{userId}/unfollow/{userIdToUnfollow")
    public ResponseEntity<Object> unfollow(@PathVariable int userId, @PathVariable int userIdToUnfollow) {
        return userService.unfollow(userId, userIdToUnfollow);
    }

    @GetMapping("/users/{userId}/followers/list")
    public UserFollowersDTO getFollowersOrder() {

    }

    @GetMapping("/users/{userId}/followed/list")
    public UserFollowersDTO getFollowersOrder() {

    }

    @GetMapping("/user/{userId}")
    public UserDTO getUser(@PathVariable int userId) {
        return userService.getUser(userId);
    }

}
