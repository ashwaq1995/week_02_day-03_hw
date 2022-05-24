package com.demo.HW3.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor @Data
public class Student {
    @NotEmpty(message = "id is required")
    private String id;

    @NotEmpty(message = "name is required")
    @Size(min = 6,max = 15,message = "You name must be more than 6 char")
    private String name;

    @NotEmpty(message = "age is required")
    @Positive(message = "You can't have negative age ")
    @Min(value = 18,message = "You must be older than 18")
    private String age;

    @NotEmpty(message = "major is required")
    private String major;

    @NotEmpty(message = "email is required")
    @Email(message = "Please send a valid email")
    private String email;
}