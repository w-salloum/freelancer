package com.home.freelancer.service;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.enums.Status;
import com.home.freelancer.exception.FreelancerNotFoundException;
import com.home.freelancer.exception.InvalidFreelancerRequestException;
import com.home.freelancer.mapper.FreelancerMapper;
import com.home.freelancer.repository.FreelancerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FreelancerService {

    private final FreelancerRepository freelancerRepository;
    private final FreelancerMapper freelancerMapper;
    private final EventService eventService;

    @Transactional
    public FreelancerResponse createFreelancer(FreelancerRequest freelancerRequest) {
        validateFreelancerRequest(freelancerRequest);
        Freelancer freelancer = freelancerMapper.toFreelancer(freelancerRequest);
        freelancer.setStatus(Status.NEW_FREELANCER);
        FreelancerResponse freelancerResponse = freelancerMapper.toFreelancerResponse(freelancerRepository.save(freelancer));
        eventService.publishFreelancerCreatedEvent(freelancerResponse);
        return freelancerResponse;
    }

    private void validateFreelancerRequest(FreelancerRequest freelancerRequest) {
        if (freelancerRequest.getFirstName() == null || freelancerRequest.getFirstName().isEmpty()) {
            throw new InvalidFreelancerRequestException("First name is required");
        }
        if (freelancerRequest.getLastName() == null || freelancerRequest.getLastName().isEmpty()) {
            throw new InvalidFreelancerRequestException("Last name is required");
        }
        if (freelancerRequest.getDateOfBirth() == null) {
            throw new InvalidFreelancerRequestException("Date of birth is required");
        }
        if (freelancerRequest.getGender() == null) {
            throw new InvalidFreelancerRequestException("Gender is required");
        }
    }

    public List<Freelancer> getAllFreelancers() {
        return freelancerRepository.findAll();
    }

    public FreelancerResponse getFreelancerById(Long id) {
        Freelancer freelancer = findFreelancerById(id);
        return freelancerMapper.toFreelancerResponse(freelancer);
    }

    public FreelancerResponse updateFreelancer(Long id, FreelancerRequest freelancerRequest) {
        Freelancer freelancer = findFreelancerById(id);
        freelancer.setFirstName(freelancerRequest.getFirstName());
        freelancer.setLastName(freelancerRequest.getLastName());
        freelancer.setDateOfBirth(freelancerRequest.getDateOfBirth());
        freelancer.setGender(freelancerRequest.getGender());
        FreelancerResponse freelancerResponse = freelancerMapper.toFreelancerResponse(freelancerRepository.save(freelancer));
        eventService.publishFreelancerUpdatedEvent(freelancerResponse);
        return freelancerResponse;
    }

    public void deleteFreelancer(Long id) {
        //validate
        Freelancer freelancer = findFreelancerById(id);
        FreelancerResponse freelancerResponse = freelancerMapper.toFreelancerResponse(freelancer);

        eventService.publishFreelancerDeletedEvent(freelancerResponse);
        freelancerRepository.deleteById(id);
    }

    // find by id, and throw exception if not found
    private Freelancer findFreelancerById(Long id) {
        return freelancerRepository.findById(id).orElseThrow(() -> new FreelancerNotFoundException("Freelancer not found"));

    }
}