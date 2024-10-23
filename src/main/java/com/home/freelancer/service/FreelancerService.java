package com.home.freelancer.service;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.mapper.FreelancerMapper;
import com.home.freelancer.repository.FreelancerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;
    private final FreelancerMapper freelancerMapper;

    public Freelancer createFreelancer(FreelancerRequest freelancerRequest) {
        return freelancerRepository.save(freelancerMapper.toFreelancer(freelancerRequest));
    }

    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    public FreelancerResponse getFreelancerById(Long id) {
        Freelancer freelancer = freelancerRepository.findById(id).orElseThrow(() -> new RuntimeException("Freelancer not found"));
        return freelancerMapper.toFreelancerResponse(freelancer);
    }

    public Freelancer updateFreelancer(Long id, Freelancer freelancerDetails) {
        Freelancer freelancer = freelancerRepository.findById(id).orElseThrow(() -> new RuntimeException("Freelancer not found"));
        freelancer.setFirstName(freelancerDetails.getFirstName());
        freelancer.setLastName(freelancerDetails.getLastName());
        freelancer.setDateOfBirth(freelancerDetails.getDateOfBirth());
        freelancer.setGender(freelancerDetails.getGender());
        return freelancerRepository.save(freelancer);
    }

    public void deleteFreelancer(Long id) {
        freelancerRepository.findById(id).orElseThrow( () -> new RuntimeException("Freelancer not found"));
        freelancerRepository.deleteById(id);
    }
}
