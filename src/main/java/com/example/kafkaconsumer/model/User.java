package com.example.kafkaconsumer.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    private String message;
    private String name;
    private Integer age;
}
