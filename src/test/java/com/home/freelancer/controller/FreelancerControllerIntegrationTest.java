package com.home.freelancer.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.enums.Gender;
import com.home.freelancer.repository.FreelancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class FreelancerControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        freelancerRepository.deleteAll(); // Clean database before each test
    }

    @Test
    void testCreateFreelancer_Success() throws Exception {
        // Arrange
        FreelancerRequest freelancerRequest = new FreelancerRequest();
        freelancerRequest.setFirstName("John");
        freelancerRequest.setLastName("Doe");
        freelancerRequest.setDateOfBirth(LocalDate.of(1990, 1, 1));
        freelancerRequest.setGender(Gender.Male);

        // Act & Assert
        mockMvc.perform(post("/api/freelancers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(freelancerRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testGetAllFreelancers() throws Exception {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        freelancer.setGender(Gender.Male);
        freelancerRepository.save(freelancer);

        // Act & Assert
        mockMvc.perform(get("/api/freelancers"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("John"));
    }

    @Test
    void testGetFreelancerById_Success() throws Exception {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setFirstName("Jane");
        freelancer.setLastName("Smith");
        freelancer.setDateOfBirth(LocalDate.of(1985, 5, 15));
        freelancer.setGender(Gender.Female);
        freelancer = freelancerRepository.save(freelancer);

        // Act & Assert
        mockMvc.perform(get("/api/freelancers/{id}", freelancer.getId()))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Jane"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Smith"));
    }

    @Test
    void testGetFreelancerById_NotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/freelancers/{id}", 999L))
                .andExpect(status().isNotFound());
    }

    @Test
    void testUpdateFreelancer_Success() throws Exception {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setFirstName("Jane");
        freelancer.setLastName("Smith");
        freelancer.setDateOfBirth(LocalDate.of(1985, 5, 15));
        freelancer.setGender(Gender.Female);
        freelancer = freelancerRepository.save(freelancer);

        FreelancerRequest updatedRequest = new FreelancerRequest();
        updatedRequest.setFirstName("Janet");
        updatedRequest.setLastName("Doe");
        updatedRequest.setDateOfBirth(LocalDate.of(1987, 7, 12));
        updatedRequest.setGender(Gender.Female);

        // Act & Assert
        mockMvc.perform(put("/api/freelancers/{id}", freelancer.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedRequest)))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Janet"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Doe"));
    }

    @Test
    void testDeleteFreelancer_Success() throws Exception {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setFirstName("Mark");
        freelancer.setLastName("Taylor");
        freelancer.setDateOfBirth(LocalDate.of(1980, 10, 10));
        freelancer.setGender(Gender.Male);
        freelancer = freelancerRepository.save(freelancer);

        // Act & Assert
        mockMvc.perform(delete("/api/freelancers/{id}", freelancer.getId()))
                .andExpect(status().isNoContent());

        // Verify the freelancer has been deleted
        assertFalse(freelancerRepository.findById(freelancer.getId()).isPresent());
    }
}