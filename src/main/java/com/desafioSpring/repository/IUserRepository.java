package com.desafioSpring.repository;

import com.desafioSpring.entity.Post;
import com.desafioSpring.entity.User;
import com.desafioSpring.entity.UserFollow;

import java.util.List;

public interface IUserRepository {

    List<User> getUsers();

    User getUserById(int userId);

    Boolean existsUser(User user);

    Boolean existsUserById(int userId);

    void update(User user);

    List<UserFollow> getFollowers(int userId);

    List<UserFollow> getFolloweds(int userId);

    void postProduct(Post post);

}
