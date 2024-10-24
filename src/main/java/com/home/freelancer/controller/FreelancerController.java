package com.home.freelancer.controller;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.service.FreelancerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/freelancers")
@RequiredArgsConstructor
public class FreelancerController {

    private final FreelancerService freelancerService;

    // Create Freelancer
    @PostMapping
    public ResponseEntity<FreelancerResponse> createFreelancer(@RequestBody FreelancerRequest freelancerRequest) {
        FreelancerResponse response = freelancerService.createFreelancer(freelancerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // Get All Freelancers
    @GetMapping
    public List<Freelancer> getAllFreelancers() {
        return freelancerService.getAllFreelancers();
    }

    // Get Freelancer by ID
    @GetMapping("/{id}")
    public ResponseEntity<FreelancerResponse> getFreelancerById(@PathVariable Long id) {
        return ResponseEntity.ok(freelancerService.getFreelancerById(id));
    }

    // Update Freelancer
    @PutMapping("/{id}")
    public ResponseEntity<FreelancerResponse> updateFreelancer(@PathVariable Long id, @RequestBody FreelancerRequest freelancerRequest) {
        return ResponseEntity.ok(freelancerService.updateFreelancer(id, freelancerRequest));
    }

    // Delete Freelancer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable Long id) {
        freelancerService.deleteFreelancer(id);
        return ResponseEntity.noContent().build();
    }
}
