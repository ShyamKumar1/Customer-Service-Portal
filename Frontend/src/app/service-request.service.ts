import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { servicerequests } from './service-request';
import { ServiceRequestListComponent } from './service-request-list/service-request-list.component';
import { UserComponent } from './user/user.component';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class ServiceRequestService {
  [x: string]: any;


  private baseUrl ="http://localhost:8090/api/v1";
  serviceName: any;
  serviceDetails:  any;
  serviceType:  any;
  serviceStatus:  any;
 date:  any;
  constructor(private http: HttpClient) { }
  getServiceRequestList():Observable<servicerequests[]>{
    return this.http.get<servicerequests[]>(`${this.baseUrl+'/AllServiceRequests'}`);
  }
  getPendningServiceRequestList():Observable<any>{
    return this.http.get<servicerequests[]>(`${this.baseUrl+'/PendingServiceRequests'}`);
  }

  getClosedServiceRequestList():Observable<servicerequests[]>{
    return this.http.get<servicerequests[]>(`${this.baseUrl+'/CloseServiceRequests'}`);
  }
  postSerReq(val:any): Observable<any[]> {
    return this.http.post<any>(this.baseUrl+'/NewServiceRequest',val);
  }

  getUser(): Observable<any[]> {
    return this.http.get<any>(this.baseUrl+'/Register');
  }
  updateServiceRequest(id: number, serviceRequest: servicerequests): Observable<Object> {
    alert("Edit successful");
    return this.http.put(`${this.baseUrl}/UpdateServiceRequest/${id}`,serviceRequest);
    }
    
    getServiceRequestById(id: number): Observable<servicerequests> {
    return this.http.get<servicerequests>(`${this.baseUrl+'/ServiceRequest/'+id}`);
    }

    deleteServiceRequest(id:number): Observable<Object>{
      return this.http.delete(`${this.baseUrl}/DeleteServiceRequest/${id}`);
    }

}

