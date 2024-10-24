package com.home.freelancer.dto;

import com.home.freelancer.enums.Gender;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FreelancerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Gender gender;
}
