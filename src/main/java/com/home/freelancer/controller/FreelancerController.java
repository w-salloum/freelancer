package com.home.freelancer.controller;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.service.FreelancerService;
import lombok.RequiredArgsConstructor;
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
    public Freelancer createFreelancer(@RequestBody FreelancerRequest freelancerRequest) {
        return freelancerService.createFreelancer(freelancerRequest);
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
    public ResponseEntity<Freelancer> updateFreelancer(@PathVariable Long id, @RequestBody Freelancer freelancerDetails) {
        return ResponseEntity.ok(freelancerService.updateFreelancer(id, freelancerDetails));
    }

    // Delete Freelancer
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable Long id) {
        freelancerService.deleteFreelancer(id);
        return ResponseEntity.noContent().build();
    }
}
