package com.desafioSpring.repository.impl;

import com.desafioSpring.entity.Post;
import com.desafioSpring.entity.User;
import com.desafioSpring.entity.UserFollow;
import com.desafioSpring.repository.IUserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Repository
public class UserRepositoryImpl implements IUserRepository {

    List<User> listUser;

    public UserRepositoryImpl() {
        this.listUser = loadFromDB();
    }

    private List<User> loadFromDB() {
        List<User> ret = null;

        File file = null;

        try {
            file = ResourceUtils.getFile("classpath:users.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var objectMapper = new ObjectMapper();
        //TypeReference<List<User>> typeRef = new TypeReference<>() {};

        try {
            ret = objectMapper.readValue(file, new TypeReference<List<User>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return ret;
    }

    @Override
    public List<User> getUsers() {
        return listUser;
    }

    @Override
    public User getUserById(int userId) {
        return listUser.stream().filter(user -> user.getUserId() == userId).findFirst().get();
    }

    @Override
    public Boolean existsUser(User user) {
        return listUser.contains(user);
    }

    @Override
    public Boolean existsUserById(int userId) {
        return existsUser(getUserById(userId));
    }

    @Override
    public void update(User user) {
        listUser.remove(getUserById(user.getUserId()));
        listUser.add(user);
    }

    @Override
    public List<UserFollow> getFollowers(int userId) {
        return getUserById(userId).getFollowers();
    }

    @Override
    public List<UserFollow> getFolloweds(int userId) {
        return getUserById(userId).getFolloweds();
    }

    @Override
    public void postProduct(Post post) {
        getUserById(post.getUserId()).getListPost().add(post);
    }

}
