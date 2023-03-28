import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RegisterService implements OnInit{

  private baseUrl = 'http://localhost:8090/api/v1/';
  firstName: any;
  lastName: any;
  email: any;
  contactNo: any;
  contactPreference: ["Email", "Phone"];
  password: any;

  constructor(private http: HttpClient) { }
  ngOnInit(): void {
  }

  createUser(val:any): Observable<any[]> {
    return this.http.post<any>(this.baseUrl+'NewRegister',val);
  }
}
