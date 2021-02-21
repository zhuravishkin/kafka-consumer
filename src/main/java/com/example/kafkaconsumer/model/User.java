package com.example.kafkaconsumer.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@ToString
@Getter
@NoArgsConstructor
public class User {
    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("sur_name")
    private String surName;

    @JsonProperty("balance")
    private Integer balance;

    @JsonProperty("event_time")
    private Long eventTime;
}
