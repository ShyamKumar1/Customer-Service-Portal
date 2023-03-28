import { Component, OnInit } from '@angular/core';
import { AuthService } from '../service/auth.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  user: User[];
  firstName: any;
  lastName: any;
  email: any;
  contactNo: any;
  contactPreference: ["Email", "Phone"];
  password: any;
  user_role:any;

  constructor(private userService: UserService, private auth: AuthService) { }

  ngOnInit(): void {
  }

  logout(): void {
    this.auth.logout();
  } 

  addNewUser() {
    const user = {
      firstName: this.firstName,
      lastName: this.lastName,
      email: this.email,
      contactNo: this.contactNo,
      contactPreference: this.contactPreference,
      password: this.password,
      user_role:this.user_role,
    };
    this.userService.createUser(user).subscribe(data => {
      console.warn(data);
      alert("User added successfully!");
      location.reload();
    });
  }

  public getUserDetails() {
    this.userService.getUserByEmail(this.email).subscribe((data: any) => {
      this.user = data;
    });
  }
}
