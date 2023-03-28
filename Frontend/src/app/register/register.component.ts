

import { Component, OnInit } from '@angular/core';
import { ServiceRequestService } from '../service-request.service';
import { FormGroup, FormControl } from '@angular/forms';
import { UserService } from '../user.service';
import { RegisterService } from '../register.service';
import { Router } from '@angular/router';


@Component({
  selector: 'register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  
  
  constructor(private userdetails:UserService, private router: Router) { }
  firstName:string;
  lastName:string;
  email:string;
  contactNo:string;
  contactPreference:string;
  password:String;
  user_role:any;


  ngOnInit() {   
    this.getUser();
  }
  addNewUser(){
    const val = {
      firstName:this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNo: this.contactNo,
      contactPreference: this.contactPreference,
      password: this.password,
      user_role:this.user_role,
    };
this.userdetails.createUser(val).subscribe(data=>{
  console.warn(data)
  alert("Registration Successful");
  this.router.navigate(['/loginpage']);
})
  }

getUser(){
  this.userdetails.getAllUsers().subscribe(data=>{
        console.warn(data)
      
       })
     }
}



  


