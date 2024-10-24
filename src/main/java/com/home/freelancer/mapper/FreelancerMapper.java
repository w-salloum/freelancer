package com.home.freelancer.mapper;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FreelancerMapper {

    Freelancer toFreelancer(FreelancerRequest freelancerRequest);
    FreelancerResponse toFreelancerResponse(Freelancer freelancer);
}
