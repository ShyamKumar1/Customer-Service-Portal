import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-update-user-list',
  templateUrl: './update-user-list.component.html',
  styleUrls: ['./update-user-list.component.css']
})
export class UpdateUserListComponent implements OnInit {
  title = 'Customer Service Request Portal';
  user: User = new User();
  email:string;

  constructor(
    private userService: UserService,
    private router: Router,
    private auth: AuthService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.email = this.route.snapshot.params['email'];
    this.userService.getUserByEmail(this.email).subscribe(
      (data) => {
        this.user = data;
      },
      (error) => console.log(error)
    );
  }

  onSubmit() {
    this.userService.updateUser(this.email, this.user).subscribe(
      (data) => {
        this.getAllUsers();
      },(error) => console.log(error));
  }

  getAllUsers() {
    this.router.navigate(['/app-user-list']);
  }
}
