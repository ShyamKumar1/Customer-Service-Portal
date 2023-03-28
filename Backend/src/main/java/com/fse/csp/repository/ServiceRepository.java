package com.fse.csp.repository;

import com.fse.csp.model.ServiceRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ServiceRepository  extends JpaRepository<ServiceRequest, Integer> {

    List<ServiceRequest> findByServiceStatus(String pending);
}
