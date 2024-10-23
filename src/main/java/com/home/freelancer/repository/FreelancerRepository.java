package com.home.freelancer.repository;

import com.home.freelancer.entity.Freelancer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FreelancerRepository extends JpaRepository<Freelancer, Long> {
}
