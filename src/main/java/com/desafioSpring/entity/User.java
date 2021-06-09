package com.desafioSpring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int userId;
    private String userName;
    private List<UserFollow> followers;
    private List<UserFollow> followeds;
    private List<Post> listPost;

    public static class UserNameComparatorASC implements Comparator<User> {
        @Override
        public int compare(User u1, User u2) {
            String n1 = u1.getUserName();
            String n2 = u2.getUserName();
            return n1.compareTo(n2);
        }
    }

    public static class UserNameComparatorDESC implements Comparator<User> {
        @Override
        public int compare(User u1, User u2) {
            String n1 = u1.getUserName();
            String n2 = u2.getUserName();
            return n2.compareTo(n1);
        }
    }

}
