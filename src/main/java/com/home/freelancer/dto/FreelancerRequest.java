package com.home.freelancer.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class FreelancerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
}
