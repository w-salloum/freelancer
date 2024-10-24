package com.home.freelancer.event;

import com.home.freelancer.dto.FreelancerResponse;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class FreelancerDeletedEvent extends FreeLancerEvent{
    public FreelancerDeletedEvent(FreelancerResponse freelancer) {
        super(freelancer);
    }
}
