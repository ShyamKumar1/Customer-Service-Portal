import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { FormsModule } from '@angular/forms';
import { UserService } from '../user.service';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { Pipe, PipeTransform } from '@angular/core';


@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})

export class UserListComponent implements OnInit {
  sessionEmail: string;

  constructor(private userService: UserService, private auth: AuthService, private router: Router) { }
  user: User[];
  firstName: any;
  lastName: any;
  email: string;
  contactNo: any;
  contactPreference: any;
  password: any;
  user_role: any;

  title = 'Customer Service Request Portal';
  ngOnInit(): void {

    // this.getAllUsers();
    const userEmail = localStorage.getItem('userEmail');
    this.sessionEmail = userEmail;
    this.userService.getUserByEmail(userEmail).subscribe((user: User) => {
      this.user_role = user.user_role;
      
      if (this.user_role === 'Admin') {
        this.getAllUsers();
      } else {
        this.userService.getUserByEmail(userEmail).subscribe((data: User) => { this.user = [data]; });
      }

    });
  }
  logout(): void {
    this.auth.logout();
  }

  public getAllUsers() {
    this.userService.getAllUsers().subscribe((data: User[]) => { this.user = data; });
  }

  updateUser(email: string) {
    this.router.navigate(['/app-update-user-list', email]);
  }
}



