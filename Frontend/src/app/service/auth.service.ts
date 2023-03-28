import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, of, throwError } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  user: User;

  constructor(private http: HttpClient, private router:Router) { }
  apiurl = ' http://localhost:3000/users'

  setToken(token: string): void {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn() {
    return this.getToken() !== null;
  }

  logout() {
    localStorage.removeItem('token');
    this.router.navigate(['/loginpage']);
  }

  login({ email, password }: any): Observable<any> {
    if (email === 'shyam.ceolife@gmail.com' && password === '123456') {
      this.setToken('abcdefghijklmnopqrstuvwxyz');
      return of({ name: 'Shyam Kumar', email: 'shyam.ceolife@gmail.com' });
    }
    else{
      return throwError(new Error('Failed to login'));
    }
  }
}


