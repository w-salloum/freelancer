package com.home.freelancer.event;

import com.home.freelancer.dto.FreelancerResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class FreeLancerEvent {
    private final FreelancerResponse freelancer;
}
