package com.home.freelancer.mapper;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.enums.Gender;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreelancerMapperTest {

    private final FreelancerMapper mapper = Mappers.getMapper(FreelancerMapper.class);

    @Test
    public void testToFreelancerResponse() {
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setDateOfBirth(LocalDate.of(1990, 1, 1));
        freelancer.setGender(Gender.Male);

        FreelancerResponse response = mapper.toFreelancerResponse(freelancer);

        assertEquals(freelancer.getId(), response.getId());
        assertEquals(freelancer.getFirstName(), response.getFirstName());
        assertEquals(freelancer.getLastName(), response.getLastName());
    }

    @Test
    public void testToFreelancer() {
        FreelancerRequest request = new FreelancerRequest();
        request.setId(1L);
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        request.setGender(Gender.Male);
        Freelancer freelancer = mapper.toFreelancer(request);
        assertEquals(request.getId(), freelancer.getId());
        assertEquals(request.getFirstName(), freelancer.getFirstName());
        assertEquals(request.getLastName(), freelancer.getLastName());

    }

}
