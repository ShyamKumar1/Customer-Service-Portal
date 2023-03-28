import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, Observable, throwError } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService implements OnInit {
  private baseUrl = 'http://localhost:8090/api/v1';

  firstName: any;
  lastName: any;
  email: any;
  contactNo: any;
  contactPreference = ["Email", "Phone"];
  password: any;

  constructor(private http: HttpClient) { }
  ngOnInit(): void {
    this.getAllUsers();
  }


  getAllUsers(): Observable<User[]> {
    return this.http.get<User[]>(`${this.baseUrl}/AllUsers`);
  }

  createUser(user: User): Observable<User> {
    return this.http.post<User>(`${this.baseUrl}/NewUser`, user);
  }

  getUserByEmail(email: String): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/GetUser/${email}`);
  }

  //   getUserById(userId: number): Observable<User> {
  //     return this.http.get<User>(`${this.baseUrl+'/user/'+userId}`);
  //   }

  updateUser(email: string, user: User): Observable<User> {
    alert("Edit successful");
    return this.http.put<User>(`${this.baseUrl}/UpdateUser/${email}`, user);
  }

  deleteUser(userId: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/DeleteUser/${userId}`);
  }

  private handleError(error: any) {
    console.error('An error occurred', error);
    return throwError(error.message || error);
  }
}
