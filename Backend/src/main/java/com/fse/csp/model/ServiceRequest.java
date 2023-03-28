package com.fse.csp.model;
import jakarta.persistence.*;
import org.hibernate.grammars.hql.HqlParser;

import java.util.Date;

@Entity
@Table(name="service_requests")
public class ServiceRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="service_name")
    private String serviceName;

    @Column(name="service_details")
    private String serviceDetails;
    @Column(name="service_type")
    private String serviceType;
    @Column(name="service_status")
    private String serviceStatus;
    String date = String.valueOf(new Date());



    public ServiceRequest(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }


    public String getServiceDetails() {
        return serviceDetails;
    }

    public void setServiceDetails(String serviceDetails) {
        this.serviceDetails = serviceDetails;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



    public ServiceRequest(String serviceName, String serviceDetails, String serviceType, String serviceStatus, String date) {
        this.serviceName = serviceName;
        this.serviceDetails = serviceDetails;
        this.serviceType = serviceType;
        this.serviceStatus = serviceStatus;
        this.date = date;

    }
}
