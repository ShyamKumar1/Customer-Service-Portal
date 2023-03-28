import { HttpClient } from '@angular/common/http';
import { Injectable, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { UserComponent } from './user/user.component';

@Injectable({
  providedIn: 'root'
})
export class LoginService implements OnInit{

  private baseUrl = 'http://localhost:8090/api/v1/';
  firstName: any;
  lastName: any;
  email: any;
  contactNo: any;
  contactPreference: ["Email", "Phone"];
  password: any;


  constructor(private httpClient: HttpClient) { }
  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }
  loginUser(email:string): Observable<UserComponent["email"]> {
    return this.httpClient.get<UserComponent["email"]>(this.baseUrl + `GetUser/`+ email);
  }
  
}
