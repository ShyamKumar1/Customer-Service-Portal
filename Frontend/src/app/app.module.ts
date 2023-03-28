import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServiceRequestListComponent } from './service-request-list/service-request-list.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';
import { RegisterComponent } from './register/register.component';
import { UserComponent } from './user/user.component';
import { LoginpageComponent } from './loginpage/loginpage.component';
import { UpdateServiceRequestComponent } from './update-service-request/update-service-request.component';
import { NgxPaginationModule } from 'ngx-pagination';
import { NotFoundComponent } from './not-found/not-found.component';
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { UserListComponent } from './user-list/user-list.component';
import { UpdateUserListComponent } from './update-user-list/update-user-list.component';


@NgModule({
  declarations: [
    AppComponent,
    ServiceRequestListComponent,
        RegisterComponent,
        UserComponent,
        LoginpageComponent,
        LoginpageComponent,
        UpdateServiceRequestComponent,
        NotFoundComponent,
        ForgotPasswordComponent,
        UserListComponent,
        UpdateUserListComponent,
  ],
  imports: [
     FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    NgxPaginationModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
