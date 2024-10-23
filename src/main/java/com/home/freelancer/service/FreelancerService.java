package com.home.freelancer.service;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.repository.FreelancerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;

    public Freelancer createFreelancer(Freelancer freelancer) {
        return freelancerRepository.save(freelancer);
    }

    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    public Optional<Freelancer> getFreelancerById(Long id) {
        return freelancerRepository.findById(id);
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
        freelancerRepository.deleteById(id);
    }
}
