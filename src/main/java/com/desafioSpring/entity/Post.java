package com.desafioSpring.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    private int userId;
    private int postId;
    @JsonFormat(pattern="dd-MM-yyyy")
    private Date date;
    private Product detail;
    private int category;
    private Double price;

    public static class DatePostComparatorASC implements Comparator<Post> {
        @Override
        public int compare(Post p1, Post p2) {
            Date d1 = p1.getDate();
            Date d2 = p2.getDate();
            return d1.compareTo(d2);
        }
    }

    public static class DatePostComparatorDESC implements Comparator<Post> {
        @Override
        public int compare(Post p1, Post p2) {
            Date d1 = p1.getDate();
            Date d2 = p2.getDate();
            return d2.compareTo(d1);
        }
    }

}
