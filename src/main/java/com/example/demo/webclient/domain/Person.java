package com.example.demo.webclient.domain;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Person {
    Integer id;
    String name;
    String username;
    String email;
    String phone;
    String website;
}
