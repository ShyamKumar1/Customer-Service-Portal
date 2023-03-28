package com.fse.csp.controller;

import com.fse.csp.exception.ResourceNotFoundException;
import com.fse.csp.model.ServiceRequest;
import com.fse.csp.model.User;
import com.fse.csp.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.cors.*;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:4200")
public class ServiceRequestController {

    @Autowired
    private ServiceRepository serviceRepository;

    // Get All Service Requests
    @GetMapping("/AllServiceRequests")
    public List<ServiceRequest> getAllServiceRequest(){
        return serviceRepository.findAll();

    }
    @GetMapping("/PendingServiceRequests")
    public List<ServiceRequest> getAllPendingServiceRequests() {
        return serviceRepository.findByServiceStatus("Pending");
    }

    @GetMapping("/CloseServiceRequests")
    public List<ServiceRequest> getAllClosedServiceRequests(){
        return serviceRepository.findByServiceStatus("Closed");
    }

    @PostMapping("/NewServiceRequest")
    public ServiceRequest createServiceRequest(@RequestBody ServiceRequest serviceRequest) {
        // save the new service request to the database
        return serviceRepository.save(serviceRequest);
    }



    @PutMapping("/UpdateServiceRequest/{id}")
    public ResponseEntity<ServiceRequest> updateServiceRequest(@PathVariable(value = "id") Integer serviceRequestId, @RequestBody ServiceRequest serviceRequestDetails) throws ResourceNotFoundException {
        // find the service request by ID
        ServiceRequest serviceRequest = serviceRepository.findById(serviceRequestId).orElseThrow(() -> new ResourceNotFoundException("Service Request not found for this id :: " + serviceRequestId));

        // update the service request with new details
        serviceRequest.setServiceName(serviceRequestDetails.getServiceName());
        serviceRequest.setServiceDetails(serviceRequestDetails.getServiceDetails());
        serviceRequest.setServiceType(serviceRequestDetails.getServiceType());
        serviceRequest.setServiceStatus(serviceRequestDetails.getServiceStatus());
        // save the updated service request to the database
        final ServiceRequest updatedServiceRequest = serviceRepository.save(serviceRequest);

        return ResponseEntity.ok(updatedServiceRequest);

    }


    @GetMapping("/ServiceRequest/{id}")
    public ResponseEntity<ServiceRequest> getServiceRequestById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        ServiceRequest serviceRequest = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Service Request not found for this id :: " + id));
        return ResponseEntity.ok().body(serviceRequest);
    }

    @DeleteMapping("/DeleteServiceRequest/{id}")
    public ResponseEntity<?> deleteServiceRequestById(@PathVariable(value = "id") Integer id) throws ResourceNotFoundException {
        ServiceRequest serviceRequest = serviceRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Service Request not found for this id :: " + id));
        serviceRepository.delete(serviceRequest);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/CloseServiceRequest/{id}")
    public ResponseEntity<ServiceRequest> closeServiceRequest(@PathVariable(value = "id") Integer Id, @RequestBody ServiceRequest serviceRequestDetails) throws ResourceNotFoundException {
        // find the service request by ID
        ServiceRequest serviceRequest = serviceRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Service Request not found for this id :: " + Id));

        // update the service request with new details
        serviceRequest.setServiceName(serviceRequestDetails.getServiceName());
        serviceRequest.setServiceDetails(serviceRequestDetails.getServiceDetails());
        serviceRequest.setServiceType(serviceRequestDetails.getServiceType());

        // set the service status to "closed"
        serviceRequest.setServiceStatus("Closed");

        // save the updated service request to the database
        final ServiceRequest updatedServiceRequest = serviceRepository.save(serviceRequest);

        return ResponseEntity.ok(updatedServiceRequest);
    }




}


