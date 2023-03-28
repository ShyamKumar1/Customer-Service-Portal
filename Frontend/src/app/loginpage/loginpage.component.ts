import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { FormControl, Validators } from '@angular/forms';
import { FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { DataService } from '../data.service';
import { LoginService } from '../login.service';
import { Token } from '@angular/compiler';

@Component({
selector: 'loginpage',
templateUrl: './loginpage.component.html',
styleUrls: ['./loginpage.component.css']
})
export class LoginpageComponent implements OnInit {
text = '';
sessionToken: string; // declare session token variable
sessionEmail:string;
constructor(private userService: UserService,
private router: Router,
private data: DataService,
private loginService: LoginService // add the login service here
) { }
ngOnInit() {
  this.UserForm = new FormGroup({
  Email: new FormControl('', Validators.required),
  Password: new FormControl('', Validators.required),
  })
  }
  
  UserForm: any;
  
  updateText(text: any) {
  this.data.updateData(text);
  }
  
  Login() {
  this.loginService.loginUser(this.UserForm.value.Email).subscribe((user: any) => { // modify the Login method to call the loginUser method of LoginService
  if (user && this.UserForm.value.Password === user.password) {
  console.log("Login is successful!")
  console.log(Response);
  
  // generate and store session token
  this.sessionToken = Math.random().toString(36).substr(2) + Math.random().toString(36).substr(2);
  localStorage.setItem('token', this.sessionToken);
  
  this.sessionEmail=this.UserForm.value.Email;
  localStorage.setItem('userEmail',this.sessionEmail);

  console.log(localStorage.getItem('token'))
  console.log(localStorage.getItem('userEmail'))
  
  
  this.router.navigate(['/app-service-request-list']);
} else {
  alert("Invalid credentials")
  }
  });
  }
  }