package com.home.freelancer.dto;

import lombok.Data;
import lombok.Value;

import java.time.LocalDate;

@Data
public class FreelancerResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String status;
}
