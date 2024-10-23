package com.home.freelancer.controller;

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
    public Freelancer createFreelancer(@RequestBody Freelancer freelancer) {
        return freelancerService.createFreelancer(freelancer);
    }

    // Get All Freelancers
    @GetMapping
    public List<Freelancer> getAllFreelancers() {
        return freelancerService.getAllFreelancers();
    }

    // Get Freelancer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Freelancer> getFreelancerById(@PathVariable Long id) {
        return freelancerService.getFreelancerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
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
