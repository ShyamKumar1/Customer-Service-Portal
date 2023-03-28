import { Component, OnInit } from '@angular/core';
import { servicerequests } from '../service-request';
import { FormsModule } from '@angular/forms';
import { ServiceRequestService } from '../service-request.service';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
@Component({
  selector: 'app-service-request-list',
  templateUrl: './service-request-list.component.html',
  styleUrls: ['./service-request-list.component.css']
})
export class ServiceRequestListComponent implements OnInit {

  title = 'Customer Service Request Portal';
  servicerequest: servicerequests[];
  id: any;
  serviceName: any;
  serviceDetails: any;
  serviceType: any;
  serviceStatus: any;
  date: any;
  currentPage: number = 1;
  pageSize: number = 10;


  constructor(private serviceRequestService: ServiceRequestService, private router: Router, private auth: AuthService) { }

  ngOnInit(): void {

    this.getPendningServiceRequestList();

  }
  logout(): void {
    this.auth.logout();
  }


  public getServiceRequests() {
    this.serviceRequestService.getServiceRequestList().subscribe(data => {
      this.servicerequest = data.slice((this.currentPage - 1) * this.pageSize, this.currentPage * this.pageSize);
    })
  }
  public getPendningServiceRequestList() {
    this.serviceRequestService.getPendningServiceRequestList().subscribe((data: servicerequests[]) => { this.servicerequest = data; })
  }

  public getClosedServiceRequestList() {
    this.serviceRequestService.getClosedServiceRequestList().subscribe((data: servicerequests[]) => { this.servicerequest = data; })
  }

  pageChanged(event: any): void {
    this.currentPage = event;
  }
  updateServiceRequest(id: number) {
    this.router.navigate(['/update-service-request', id]);
  }

  deleteServiceRequest(id: number) {
    this.serviceRequestService.deleteServiceRequest(id).subscribe(data => {
      alert("Request with Id " + id + " is deleted");
      this.getServiceRequests();
    })
  }

  closeServiceRequest(id: number, status: string = 'Closed') {
    const serviceReqToUpdate = this.servicerequest.find(sr => sr.id === id);
    if (serviceReqToUpdate) {
      serviceReqToUpdate.serviceStatus = status;
      this.serviceRequestService.updateServiceRequest(id, serviceReqToUpdate).subscribe(() => {
        console.log(`Service request ${id} updated with status ${status}`);
        location.reload();
      }, (error) => {
        console.error(`Failed to update service request ${id}: ${error}`);
      });
    } else {
      console.error(`Service request with ID ${id} not found`);
    }
  }

  addServiceRequestBtn() {
    var addSerReq = {
      id: this.id,
      serviceName: this.serviceName,
      serviceDetails: this.serviceDetails,
      serviceType: this.serviceType,
      serviceStatus: this.serviceStatus,
      date: this.date
    }
    this.serviceRequestService.postSerReq(addSerReq).subscribe(data => {
      console.warn(data)
      alert("Service request added successfully!");
      location.reload();
    })



  }



}





