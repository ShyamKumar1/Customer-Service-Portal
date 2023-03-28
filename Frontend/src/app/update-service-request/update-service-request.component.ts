import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { servicerequests } from '../service-request';
import { ServiceRequestListComponent } from '../service-request-list/service-request-list.component';
import { ServiceRequestService } from '../service-request.service';

@Component({
  selector: 'update-service-request',
  templateUrl: './update-service-request.component.html',
  styleUrls: ['./update-service-request.component.css']
})
export class UpdateServiceRequestComponent implements OnInit {

  id: number;
  serviceRequest: servicerequests = new servicerequests();
  constructor(private serviceRequestService: ServiceRequestService, private route: ActivatedRoute, private router: Router) { }
  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.serviceRequestService.getServiceRequestById(this.id).subscribe(data => {
      this.serviceRequest = data;
    }, error => console.log(error));
  }

  onSubmit() {
    this.serviceRequestService.updateServiceRequest(this.id, this.serviceRequest).subscribe(data => {
      this.getServiceRequests();
    }, error => console.log(error));
  }
  getServiceRequests() {
    this.router.navigate(['/app-service-request-list']);
  }

}
