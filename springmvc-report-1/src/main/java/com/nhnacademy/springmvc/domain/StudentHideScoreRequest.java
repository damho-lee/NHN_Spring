package com.nhnacademy.springmvc.domain;

import lombok.Value;

@Value
public class StudentHideScoreRequest {
    long id;
    String name;
    String email;
    String score;
    String comment;
}
