package com.desafioSpring.service.impl;

import com.desafioSpring.dto.*;
import com.desafioSpring.entity.Post;
import com.desafioSpring.entity.User;
import com.desafioSpring.entity.UserFollow;
import com.desafioSpring.exception.UserAlreadyFollowedException;
import com.desafioSpring.exception.UserNotAlreadyFollowedException;
import com.desafioSpring.exception.UserNotFoundException;
import com.desafioSpring.exception.UserToFollowIsNotSellerException;
import com.desafioSpring.repository.IUserRepository;
import com.desafioSpring.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserRepository userRepository;

    @Override
    public UserDTO getUser(int userId) {
        User user = userRepository.getUserById(userId);
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(user, UserDTO.class);
    }

    @Override
    public ResponseEntity<Object> follow(int userId, int userIdToFollow) throws UserNotFoundException {
        if(validateUser(userId) && validateUser(userIdToFollow)) {
            if(!alreadyFollow(userId, userIdToFollow)) {
                if(isSeller(userIdToFollow)) {
                    User userFrom = userRepository.getUserById(userId);
                    User userToFollow = userRepository.getUserById(userIdToFollow);
                    userFrom.getFolloweds().add(new UserFollow(userIdToFollow, userToFollow.getUserName()));
                    userToFollow.getFollowers().add(new UserFollow(userId, userFrom.getUserName()));
                    userRepository.update(userFrom);
                    userRepository.update(userToFollow);
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else throw new UserToFollowIsNotSellerException();
            }
            else throw new UserAlreadyFollowedException();
        }
        else throw new UserNotFoundException();
    }

    @Override
    public UserFollowersCountDTO followersCount(int userId) {
        return new UserFollowersCountDTO(userId, userRepository.getUserById(userId).getUserName(),
                listOfFollowers(userId).getFollowers().size());
    }

    @Override
    public UserFollowersDTO listOfFollowers(int userId) {
        if(validateUser(userId)) {
            List<UserFollowDTO> listOfFollowersDTO = new ArrayList<>();
            for(UserFollow u : userRepository.getFollowers(userId)) {
                listOfFollowersDTO.add(new UserFollowDTO(u.getUserId(), u.getUserName()));
            }
            return new UserFollowersDTO(userId, userRepository.getUserById(userId).getUserName(), listOfFollowersDTO);
        }
        return null;
    }

    @Override
    public UserFollowersDTO listOfSellersFollowed(int userId) {
        if(validateUser(userId)) {
            List<UserFollowDTO> listOfFollowedsDTO = new ArrayList<>();
            for(UserFollow u : userRepository.getFolloweds(userId)) {
                if(isSeller(u.getUserId())) {
                    listOfFollowedsDTO.add(new UserFollowDTO(u.getUserId(), u.getUserName()));
                }
            }
            return new UserFollowersDTO(userId, userRepository.getUserById(userId).getUserName(), listOfFollowedsDTO);
        }
        return null;
    }

    @Override
    public PostSellerFollowedDTO listPostsSellersFollowed(int userId) {
        ModelMapper modelMapper = new ModelMapper();
        List<UserFollow> usersFollowed = userRepository.getFolloweds(userId);
        List<PostDTO> postsSellersFollowed = new ArrayList<>();
        for(UserFollow uf : usersFollowed) {
            User u = userRepository.getUserById(uf.getUserId());
            u.getListPost().stream().filter(post -> {
                if (isFromLastTwoWeeks(post)) {
                    postsSellersFollowed.add(modelMapper.map(post, PostDTO.class));
                }
                return false;
            });
            /*for(Post p : u.getListPost()) {
                if(isFromLastTwoWeeks(p)) {
                    postsSellersFollowed.add(modelMapper.map(p, PostDTO.class));
                }
            }*/
        }
        return new PostSellerFollowedDTO(userId, postsSellersFollowed);
    }

    @Override
    public ResponseEntity<Object> unfollow(int userId, int userIdToUnfollow) {
        if(validateUser(userId) && validateUser(userIdToUnfollow)) {
            if(alreadyFollow(userId, userIdToUnfollow)) {
                User userFrom = userRepository.getUserById(userId);
                User userToUnfollow = userRepository.getUserById(userIdToUnfollow);
                userFrom.getFolloweds().remove(new UserFollow(userIdToUnfollow, userToUnfollow.getUserName()));
                userToUnfollow.getFollowers().remove(new UserFollow(userId, userFrom.getUserName()));
                userRepository.update(userFrom);
                userRepository.update(userToUnfollow);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else throw new UserNotAlreadyFollowedException();
        }
        else throw new UserNotFoundException();
    }

    private Boolean validateUser(int userId) throws UserNotFoundException {
        if(userRepository.existsUserById(userId)) {
            return true;
        }
        else {
            throw new UserNotFoundException();
        }
    }

    private Boolean alreadyFollow(int userIdFrom, int userIdTo) {
        UserFollowDTO userTo = new UserFollowDTO(userRepository.getUserById(userIdTo).getUserId(),
                userRepository.getUserById(userIdTo).getUserName());
        return userRepository.getFolloweds(userIdFrom).contains(userTo);
    }

    private Boolean isSeller(int userId) {
        if(validateUser(userId)) {
            return !userRepository.getUserById(userId).getListPost().isEmpty();
        }
        else {
            throw new UserNotFoundException();
        }
    }

    private Boolean isFromLastTwoWeeks(Post post) {
        long today = Calendar.getInstance().getTime().getTime();
        long difference = today - post.getDate().getTime();
        long daysDifference = (difference / (1000*60*60*24)) % 365;
        return daysDifference < 14;
    }

}
