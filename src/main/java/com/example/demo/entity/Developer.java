package com.example.demo.entity;

import lombok.*;

import java.util.List;
import java.util.random.RandomGenerator;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Developer {
    private long id = RandomGenerator.getDefault().nextLong();
    private String name;
    private String department;
    private List<String> stacks;
}


/*
*
* 1. create developer
* 2. update Developer
* 3. getAllDevs
*
*/