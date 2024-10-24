package com.home.freelancer.service;

import com.home.freelancer.dto.FreelancerRequest;
import com.home.freelancer.dto.FreelancerResponse;
import com.home.freelancer.entity.Freelancer;
import com.home.freelancer.enums.Gender;
import com.home.freelancer.exception.InvalidFreelancerRequestException;
import com.home.freelancer.mapper.FreelancerMapper;
import com.home.freelancer.repository.FreelancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FreelancerServiceTest {

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private FreelancerMapper freelancerMapper;

    @Mock
    private EventService eventService;

    @InjectMocks
    private FreelancerService freelancerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateFreelancer_Success() {
        // Arrange
        FreelancerRequest request = new FreelancerRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        request.setGender(Gender.MALE);

        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        FreelancerResponse response = new FreelancerResponse();
        response.setId(1L);
        when(freelancerMapper.toFreelancer(any(FreelancerRequest.class))).thenReturn(freelancer);
        when(freelancerMapper.toFreelancerResponse(any(Freelancer.class))).thenReturn(response);
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);

        // Act
        FreelancerResponse createdFreelancer = freelancerService.createFreelancer(request);

        // Assert
        assertNotNull(createdFreelancer);
        assertEquals(1L, createdFreelancer.getId());
        verify(freelancerRepository, times(1)).save(any(Freelancer.class));
    }

    @Test
    void testCreateFreelancer_InvalidRequest_FirstNameMissing() {
        // Arrange
        FreelancerRequest request = new FreelancerRequest();
        request.setLastName("Doe");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        request.setGender(Gender.MALE);

        // Act & Assert
        InvalidFreelancerRequestException exception = assertThrows(InvalidFreelancerRequestException.class,
                () -> freelancerService.createFreelancer(request));

        assertEquals("First name is required", exception.getMessage());
    }

    @Test
    void testGetAllFreelancers() {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        when(freelancerRepository.findAll()).thenReturn(Collections.singletonList(freelancer));

        // Act
        List<Freelancer> freelancers = freelancerService.getAllFreelancers();

        // Assert
        assertNotNull(freelancers);
        assertEquals(1, freelancers.size());
        verify(freelancerRepository, times(1)).findAll();
    }

    @Test
    void testGetFreelancerById_Success() {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));

        FreelancerResponse response = new FreelancerResponse();
        response.setId(1L);
        when(freelancerMapper.toFreelancerResponse(any(Freelancer.class))).thenReturn(response);

        // Act
        FreelancerResponse freelancerResponse = freelancerService.getFreelancerById(1L);

        // Assert
        assertNotNull(freelancerResponse);
        assertEquals(1L, freelancerResponse.getId());
        verify(freelancerRepository, times(1)).findById(1L);
    }

    @Test
    void testGetFreelancerById_NotFound() {
        // Arrange
        when(freelancerRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class,
                () -> freelancerService.getFreelancerById(1L));

        assertEquals("Freelancer not found", exception.getMessage());
    }

    @Test
    void testUpdateFreelancer_Success() {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);

        FreelancerRequest request = new FreelancerRequest();
        request.setFirstName("John");
        request.setLastName("Doe");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        request.setGender(Gender.MALE);

        FreelancerResponse response = new FreelancerResponse();
        response.setId(1L);
        when(freelancerMapper.toFreelancerResponse(any(Freelancer.class))).thenReturn(response);

        // Act
        FreelancerResponse updatedFreelancer = freelancerService.updateFreelancer(1L, request);

        // Assert
        assertNotNull(updatedFreelancer);
        verify(freelancerRepository, times(1)).save(any(Freelancer.class));
    }

    @Test
    void testDeleteFreelancer_Success() {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));

        // Act
        freelancerService.deleteFreelancer(1L);

        // Assert
        verify(freelancerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testApproveFreelancer_Success() {
        // Arrange
        Freelancer freelancer = new Freelancer();
        freelancer.setId(1L);
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);

        FreelancerResponse response = new FreelancerResponse();
        response.setId(1L);
        when(freelancerMapper.toFreelancerResponse(any(Freelancer.class))).thenReturn(response);

        // Act
        FreelancerResponse approvedFreelancer = freelancerService.approveFreelancer(1L);

        // Assert
        assertNotNull(approvedFreelancer);
        verify(freelancerRepository, times(1)).save(any(Freelancer.class));
    }
}
