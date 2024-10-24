package com.home.freelancer.event;

import com.home.freelancer.dto.FreelancerResponse;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Jacksonized
public class FreelancerUpdatedEvent extends FreeLancerEvent{
    public FreelancerUpdatedEvent(FreelancerResponse freelancer) {
        super(freelancer);
    }
}
