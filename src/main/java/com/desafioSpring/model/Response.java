package com.desafioSpring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private Timestamp timestamp;
    private String data;
    private int responseCode;
    private String status;

}