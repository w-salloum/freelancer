package com.home.freelancer.mapper;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FreelancerMapper {

    FreelancerMapper INSTANCE = Mappers.getMapper(FreelancerMapper.class);
    Freelancer toFreelancer(FreelancerRequest freelancerRequest);
    FreelancerResponse toFreelancerResponse(Freelancer freelancer);
}
