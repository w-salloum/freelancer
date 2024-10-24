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

/**
 * Controller for managing freelancers.
 */
@RestController
@RequestMapping("/api/freelancers")
@RequiredArgsConstructor
public class FreelancerController {

    private final FreelancerService freelancerService;

    /**
     * Creates a new freelancer.
     *
     * @param freelancerRequest the details of the freelancer to create
     * @return ResponseEntity containing the created FreelancerResponse and HTTP status 201 (Created)
     */
    @PostMapping
    public ResponseEntity<FreelancerResponse> createFreelancer(@RequestBody FreelancerRequest freelancerRequest) {
        FreelancerResponse response = freelancerService.createFreelancer(freelancerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Retrieves all freelancers.
     *
     * @return a list of all freelancers
     */
    @GetMapping
    public List<Freelancer> getAllFreelancers() {
        return freelancerService.getAllFreelancers();
    }

    /**
     * Retrieves a freelancer by their ID.
     *
     * @param id the ID of the freelancer to retrieve
     * @return ResponseEntity containing the FreelancerResponse and HTTP status 200 (OK)
     */
    @GetMapping("/{id}")
    public ResponseEntity<FreelancerResponse> getFreelancerById(@PathVariable Long id) {
        return ResponseEntity.ok(freelancerService.getFreelancerById(id));
    }

    /**
     * Updates an existing freelancer.
     *
     * @param id                the ID of the freelancer to update
     * @param freelancerRequest the updated details of the freelancer
     * @return ResponseEntity containing the updated FreelancerResponse and HTTP status 200 (OK)
     */
    @PutMapping("/{id}")
    public ResponseEntity<FreelancerResponse> updateFreelancer(@PathVariable Long id, @RequestBody FreelancerRequest freelancerRequest) {
        return ResponseEntity.ok(freelancerService.updateFreelancer(id, freelancerRequest));
    }

    /**
     * Deletes a freelancer by their ID.
     *
     * @param id the ID of the freelancer to delete
     * @return ResponseEntity with HTTP status 204 (No Content)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFreelancer(@PathVariable Long id) {
        freelancerService.deleteFreelancer(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Approves a freelancer by their ID.
     *
     * @param id the ID of the freelancer to approve
     * @return ResponseEntity containing the approved FreelancerResponse and HTTP status 200 (OK)
     */
    @PutMapping("/{id}/approve")
    public ResponseEntity<FreelancerResponse> approveFreelancer(@PathVariable Long id) {
        return ResponseEntity.ok(freelancerService.approveFreelancer(id));
    }
}