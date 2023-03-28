package com.fse.csp;

import com.fse.csp.controller.ServiceRequestController;
import com.fse.csp.exception.ResourceNotFoundException;
import com.fse.csp.model.ServiceRequest;
import com.fse.csp.repository.ServiceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ServiceRequestControllerTest {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceRequestController serviceRequestController;

    private ServiceRequest serviceRequest1;
    private ServiceRequest serviceRequest2;
    private ServiceRequest updatedServiceRequest;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        serviceRequest1 = new ServiceRequest("Service name 1", "Service 1", "Service details 1", "Pending", "2023-03-09T20:37:54.671+00:00");
        serviceRequest2 = new ServiceRequest("Service name 2", "Service 2", "Service details 2", "Closed", "2023-03-09T20:37:54.671+00:00");
        updatedServiceRequest = new ServiceRequest("Service name 1 Updated", "Service 1 Updated", "Service details 1 Updated", "Type 1 Updated", "Pending");
    }

    @Test
    void getAllServiceRequest() {
        List<ServiceRequest> serviceRequests = Arrays.asList(serviceRequest1, serviceRequest2);

        when(serviceRepository.findAll()).thenReturn(serviceRequests);

        List<ServiceRequest> result = serviceRequestController.getAllServiceRequest();

        assertEquals(2, result.size());
        assertEquals(serviceRequests, result);

        verify(serviceRepository, times(1)).findAll();
    }

    @Test
    void getAllPendingServiceRequests() {
        List<ServiceRequest> serviceRequests = Arrays.asList(serviceRequest1);

        when(serviceRepository.findByServiceStatus("Pending")).thenReturn(serviceRequests);

        List<ServiceRequest> result = serviceRequestController.getAllPendingServiceRequests();

        assertEquals(1, result.size());
        assertEquals(serviceRequests, result);

        verify(serviceRepository, times(1)).findByServiceStatus("Pending");
    }

    @Test
    void getAllClosedServiceRequests() {
        List<ServiceRequest> serviceRequests = Arrays.asList(serviceRequest2);

        when(serviceRepository.findByServiceStatus("Closed")).thenReturn(serviceRequests);

        List<ServiceRequest> result = serviceRequestController.getAllClosedServiceRequests();

        assertEquals(1, result.size());
        assertEquals(serviceRequests, result);

        verify(serviceRepository, times(1)).findByServiceStatus("Closed");
    }

    @Test
    void createServiceRequest() {
        when(serviceRepository.save(serviceRequest1)).thenReturn(serviceRequest1);

        ServiceRequest result = serviceRequestController.createServiceRequest(serviceRequest1);

        assertEquals(serviceRequest1, result);

        verify(serviceRepository, times(1)).save(serviceRequest1);
    }

    @Test
    void updateServiceRequest() throws ResourceNotFoundException {
        Integer serviceRequestId = 1;

        // create a mock ServiceRequest object with updated values
        ServiceRequest updatedServiceRequest = new ServiceRequest();
        updatedServiceRequest.setServiceName("Updated Service Name");
        updatedServiceRequest.setServiceDetails("Updated Service Details");
        updatedServiceRequest.setServiceType("Updated Service Type");
        updatedServiceRequest.setServiceStatus("Updated Service Status");

        // set up the mock repository to return the original service request and the updated service request when called
        when(serviceRepository.findById(serviceRequestId)).thenReturn(Optional.of(serviceRequest1));
        when(serviceRepository.save(any(ServiceRequest.class))).thenReturn(updatedServiceRequest);

        // call the updateServiceRequest method and capture the response entity
        ResponseEntity<ServiceRequest> result = serviceRequestController.updateServiceRequest(serviceRequestId, updatedServiceRequest);

        // assert that the response entity has a status code of OK and contains the updated service request
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(updatedServiceRequest, result.getBody());

        // verify that the repository's findById and save methods were each called once with the correct arguments
        verify(serviceRepository, times(1)).findById(serviceRequestId);
        verify(serviceRepository, times(1)).save(serviceRequest1);
    }


    @Test
    void updateServiceRequest_NotFoundException() {
        Integer serviceRequestId = 1;

        when(serviceRepository.findById(serviceRequestId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> serviceRequestController.updateServiceRequest(serviceRequestId, updatedServiceRequest));

        verify(serviceRepository, times(1)).findById(serviceRequestId);
        verify(serviceRepository, never()).save(any(ServiceRequest.class));
    }

    @Test
    public void testGetServiceRequestById() throws ResourceNotFoundException {
        Integer id = 1;
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(id);
        serviceRequest.setServiceName("Service 1");
        serviceRequest.setServiceDetails("Service 1 details");
        serviceRequest.setServiceType("Type 1");
        serviceRequest.setServiceStatus("Open");

        Mockito.when(serviceRepository.findById(id)).thenReturn(Optional.of(serviceRequest));

        ResponseEntity<ServiceRequest> responseEntity = serviceRequestController.getServiceRequestById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody().getId());
        assertEquals("Service 1", responseEntity.getBody().getServiceName());
        assertEquals("Service 1 details", responseEntity.getBody().getServiceDetails());
        assertEquals("Type 1", responseEntity.getBody().getServiceType());
        assertEquals("Open", responseEntity.getBody().getServiceStatus());
    }



    @Test
    public void shouldThrowResourceNotFoundExceptionWhenServiceRequestByIdNotFound() throws ResourceNotFoundException {
// Arrange
        Integer id = 2;
        Mockito.when(serviceRepository.findById(id)).thenReturn(Optional.empty());
        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> serviceRequestController.getServiceRequestById(id));
    }


        @Test
    public void testDeleteServiceRequestById() throws ResourceNotFoundException {
        Integer id = 1;
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(id);
        serviceRequest.setServiceName("Service 1");
        serviceRequest.setServiceDetails("Service 1 details");
        serviceRequest.setServiceType("Type 1");
        serviceRequest.setServiceStatus("Open");

        Mockito.when(serviceRepository.findById(id)).thenReturn(Optional.of(serviceRequest));

        ResponseEntity<?> responseEntity = serviceRequestController.deleteServiceRequestById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }


    @Test
    public void testCloseServiceRequest() throws ResourceNotFoundException {
        Integer id = 1;
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setId(id);
        serviceRequest.setServiceName("Service 1");
        serviceRequest.setServiceDetails("Service 1 details");
        serviceRequest.setServiceType("Type 1");
        serviceRequest.setServiceStatus("Open");

        ServiceRequest serviceRequestDetails = new ServiceRequest();
        serviceRequestDetails.setServiceName("Service 1 updated");
        serviceRequestDetails.setServiceDetails("Service 1 details updated");
        serviceRequestDetails.setServiceType("Type 1 updated");

        Mockito.when(serviceRepository.findById(id)).thenReturn(Optional.of(serviceRequest));
        Mockito.when(serviceRepository.save(serviceRequest)).thenReturn(serviceRequest);

        ResponseEntity<ServiceRequest> responseEntity = serviceRequestController.closeServiceRequest(id, serviceRequestDetails);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(id, responseEntity.getBody().getId());
        assertEquals("Service 1 updated", responseEntity.getBody().getServiceName());
        assertEquals("Service 1 details updated", responseEntity.getBody().getServiceDetails());
        assertEquals("Type 1 updated", responseEntity.getBody().getServiceType());
        assertEquals("Closed", responseEntity.getBody().getServiceStatus());
    }





}
