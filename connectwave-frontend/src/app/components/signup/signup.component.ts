import { HttpErrorResponse } from '@angular/common/http';
import { Component, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent {

  constructor(private auth: AuthService, private router: Router) {}

  user: User = {
    id: 0,
    email: '',
    password: '',
    firstName: '',
    lastName: '',
    bio: '',
    createdDate: undefined,
    lastLoginDate: undefined
  };

  signupFormSubmitted = false;
  @ViewChild('signupForm') signupForm: NgForm | undefined;

  onSignupSubmit() {
    this.signupFormSubmitted = true;

    if (this.signupForm!.valid) {
      this.auth.signup(this.user).subscribe(
        (response) => {
          console.log("successful signup!")
        },
        (error: HttpErrorResponse) => {
          console.error(error.message);
          this.router.navigate(['/home']);
        }
      );
    }
  }

  navToLogin() {
    this.router.navigate(['/login']);
  }
}
