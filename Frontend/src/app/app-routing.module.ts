import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { ServiceRequestListComponent } from './service-request-list/service-request-list.component';
import { UpdateServiceRequestComponent } from './update-service-request/update-service-request.component';
import { NotFoundComponent } from './not-found/not-found.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { AuthGuard } from './gaurd/auth.guard';
import { UserListComponent } from './user-list/user-list.component';
import { UpdateUserListComponent } from './update-user-list/update-user-list.component';

const routes: Routes = [
  { path: '', redirectTo: "loginpage", pathMatch: "full" },
  { path: 'loginpage', component: LoginpageComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', component: UserComponent },
  { path: 'request', component: Request },
  { path: 'app-forgot-password', component: ForgotPasswordComponent },
  { path: 'app-service-request-list', component: ServiceRequestListComponent, canActivate: [AuthGuard] },
  { path: 'update-service-request/:id', component: UpdateServiceRequestComponent },
  { path: 'app-user', component: UserComponent },
  { path: 'app-user-list', component: UserListComponent },
  { path: 'app-update-user-list/:email', component: UpdateUserListComponent },
  { path: '**', component: NotFoundComponent },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
