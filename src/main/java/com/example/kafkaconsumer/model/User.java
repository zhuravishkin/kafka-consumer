package com.example.kafkaconsumer.model;

import lombok.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String message;

    private String name;

    private Integer age;
}
